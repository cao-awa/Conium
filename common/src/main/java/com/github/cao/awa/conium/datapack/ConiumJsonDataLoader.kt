package com.github.cao.awa.conium.datapack

import com.github.cao.awa.conium.server.ConiumDedicatedServer
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import net.minecraft.resource.Resource
import net.minecraft.resource.ResourceFinder
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.SinglePreparationResourceReloader
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.BufferedReader

abstract class ConiumJsonDataLoader(private val dataType: Identifier) : SinglePreparationResourceReloader<MutableMap<Identifier, JsonElement>>() {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumJsonDataLoader")
    }

    fun earlyPrepare(resourceManager: ResourceManager) {
        val results: MutableMap<Identifier, JsonElement> = CollectionFactor.hashMap()
        load(resourceManager, this.dataType, results)
        earlyLoad(resourceManager, this.dataType, results)
    }

    abstract fun earlyLoad(
        manager: ResourceManager,
        dataType: Identifier,
        result: MutableMap<Identifier, JsonElement>
    )

    override fun prepare(resourceManager: ResourceManager, profiler: Profiler?): MutableMap<Identifier, JsonElement> = CollectionFactor.hashMap<Identifier, JsonElement>().also {
        load(resourceManager, this.dataType, it)
    }

    private fun load(
        manager: ResourceManager,
        dataType: Identifier,
        result: MutableMap<Identifier, JsonElement>
    ) {
        val resourceFinder: ResourceFinder = ResourceFinder.json(dataType.path)

        for ((identifier: Identifier, value: Resource) in resourceFinder.findResources(manager)) {
            try {
                val reader: BufferedReader = value.reader

                try {
                    JsonParser.parseReader(reader).let { json ->
                        result[identifier] = json

                        if (ConiumDedicatedServer.initialized) {
                            ConiumDedicatedServer.onLoadData(dataType, identifier, json.toString())
                        }
                    }
                } catch (var14: Throwable) {
                    try {
                        reader.close()
                    } catch (var13: Throwable) {
                        var14.addSuppressed(var13)
                    }

                    throw var14
                }

                reader.close()
            } catch (var15: Exception) {
                LOGGER.error("Couldn't parse data file '{}' from '{}'", resourceFinder.toResourceId(identifier), identifier, var15)
            }
        }
    }
}
