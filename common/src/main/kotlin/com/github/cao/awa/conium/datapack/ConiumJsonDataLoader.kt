package com.github.cao.awa.conium.datapack

import com.github.cao.awa.conium.server.ConiumDedicatedServer
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import net.minecraft.server.packs.resources.Resource
import net.minecraft.resources.FileToIdConverter
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.SimplePreparableReloadListener
import net.minecraft.resources.Identifier
import net.minecraft.util.profiling.ProfilerFiller
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.BufferedReader

abstract class ConiumJsonDataLoader(private val dataType: Identifier) : SimplePreparableReloadListener<MutableMap<Identifier, JsonElement>>() {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumJsonDataLoader")
    }

    fun earlyPrepare(resourceManager: ResourceManager) {
        val results: MutableMap<Identifier, JsonElement> = HashMap()
        load(resourceManager, this.dataType, results)
        earlyLoad(resourceManager, this.dataType, results)
    }

    open fun earlyLoad(
        manager: ResourceManager,
        dataType: Identifier,
        result: MutableMap<Identifier, JsonElement>
    ) {
        // Default do nothing.
    }

    override fun prepare(resourceManager: ResourceManager, profiler: ProfilerFiller): MutableMap<Identifier, JsonElement> = HashMap<Identifier, JsonElement>().also {
        load(resourceManager, this.dataType, it)
    }

    private fun load(
        manager: ResourceManager,
        dataType: Identifier,
        result: MutableMap<Identifier, JsonElement>
    ) {
        val resourceFinder: FileToIdConverter = FileToIdConverter.json(dataType.path)

        for ((identifier: Identifier, value: Resource) in resourceFinder.listMatchingResources(manager)) {
            try {
                val reader: BufferedReader = value.openAsReader()

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
                LOGGER.error("Couldn't parse data file '{}' from '{}'", resourceFinder.fileToId(identifier), identifier, var15)
            }
        }
    }
}
