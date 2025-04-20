package com.github.cao.awa.conium.block.template.preset

import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.registry.RegistryWrapper

class ConiumBlockEntityPresetsTemplate(private val presetData: JsonObject, private val registryLookup: RegistryWrapper.WrapperLookup) : ConiumBlockTemplate(name = ConiumTemplates.Block.BLOCK_ENTITY_PRESETS) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumBlockEntityPresetsTemplate = element.asJsonObject.ifJsonObject(
            {
                ConiumBlockEntityPresetsTemplate(it, registryLookup)
            },
            notSupported()
        )!!
    }

    override fun settings(settings: ConiumBlockSettings) {
        // Deserialize block entity templates.
        deserializeBlockEntityTemplates(
            this.presetData,
            this.registryLookup
        ).also { templates ->
            // Add all deserialized templates to settings.
            settings.blockEntity.blockEntityTemplates.addAll(
                // Deserialize the block entity templates.
                templates
            )
        }
    }
}