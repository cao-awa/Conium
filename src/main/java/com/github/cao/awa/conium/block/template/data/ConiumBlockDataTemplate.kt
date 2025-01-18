package com.github.cao.awa.conium.block.template.data

import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.template.ConiumTemplates
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.registry.RegistryWrapper

class ConiumBlockDataTemplate(private val registered: Map<String, ConiumNbtDataSerializer<*>>, private val defaultValues: Map<String, Any>): ConiumBlockTemplate(name = ConiumTemplates.Block.DATA) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumBlockDataTemplate {
            element as JsonObject

            val registered = CollectionFactor.hashMap<String, ConiumNbtDataSerializer<*>>()
            val defaultValues = CollectionFactor.hashMap<String, Any>()

            element.keySet().forEach { key ->
                val value = element[key] as JsonObject
                val serializer = ConiumNbtDataSerializer.getSerializer(value["type"].asString)
                registered[key] = serializer
                defaultValues[key] = serializer.readFromJson(value, registryLookup, "value")
            }

            return ConiumBlockDataTemplate(
                registered,
                defaultValues
            )
        }
    }

    override fun settings(settings: ConiumBlockSettings) {
        settings.blockEntity.registeredData = this.registered
        settings.blockEntity.defaultData = this.defaultValues
    }
}
