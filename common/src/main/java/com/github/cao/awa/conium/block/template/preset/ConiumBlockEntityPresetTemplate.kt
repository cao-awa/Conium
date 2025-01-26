package com.github.cao.awa.conium.block.template.preset

import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumBlockEntityPresetTemplate(private val presetData: JsonObject, private val registryLookup: WrapperLookup) : ConiumBlockTemplate(name = ConiumTemplates.Block.BLOCK_ENTITY_PRESET) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBlockEntityPresetTemplate = element.asJsonObject.ifJsonObject(
            {
                ConiumBlockEntityPresetTemplate(it, registryLookup)
            },
            notSupported()
        )!!
    }

    override fun settings(settings: ConiumBlockSettings) {
        println("Deserializing: ${this.presetData}")

        deserializeBlockEntityTemplates(
            this.presetData,
            this.registryLookup
        ).also { templates ->
            println(templates)

            // Add all deserialized templates to settings.
            settings.blockEntity.blockEntityTemplates.addAll(
                // Deserialize the block entity templates.
                templates
            )
        }
    }
}