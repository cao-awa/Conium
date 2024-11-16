package com.github.cao.awa.conium.datapack

import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import net.minecraft.resource.ResourceFinder
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.SinglePreparationResourceReloader
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

abstract class ConiumJsonDataLoader(private val dataType: String) :
    SinglePreparationResourceReloader<MutableMap<Identifier, JsonElement>>() {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumJsonDataLoader")
    }

    override fun prepare(resourceManager: ResourceManager, profiler: Profiler): MutableMap<Identifier, JsonElement> =
        CollectionFactor.hashMap<Identifier, JsonElement>().also {
            load(resourceManager, this.dataType, it)
        }

    private fun load(
        manager: ResourceManager,
        dataType: String,
        result: MutableMap<Identifier, JsonElement>
    ) {
        val resourceFinder = ResourceFinder.json(dataType)

        for ((identifier, value) in resourceFinder.findResources(manager)) {
            try {
                val reader = value.reader

                try {
                    result[identifier] = JsonParser.parseReader(reader)
                } catch (var14: Throwable) {
                    try {
                        reader?.close()
                    } catch (var13: Throwable) {
                        var14.addSuppressed(var13)
                    }

                    throw var14
                }

                reader?.close()
            } catch (var15: Exception) {
                LOGGER.error("Couldn't parse data file '{}' from '{}'", resourceFinder.toResourceId(identifier), identifier, var15)
            }
        }
    }
}
