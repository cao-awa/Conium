package com.github.cao.awa.conium.item.template.bedrock.glint

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Item.GLINT
import com.google.gson.JsonElement
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumBedrockGlintTemplate(private val glint: Boolean) : ConiumItemTemplate(GLINT) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBedrockGlintTemplate {
            return if (element.isJsonObject) {
                // Bedrock schema is:
                // "minecraft:glint": {
                //     "value": <bool>
                // }
                ConiumBedrockGlintTemplate(element.asJsonObject["value"].asBoolean)
            } else if (element.isJsonPrimitive) {
                // Conium additional supporting schema:
                // "minecraft:glint": <bool>
                ConiumBedrockGlintTemplate(element.asBoolean)
            } else {
                throw IllegalArgumentException("Not supported syntax: $element")
            }
        }
    }

    override fun settings(settings: Item.Settings) {
        // Set glint override.
        settings.component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, this.glint)
    }
}
