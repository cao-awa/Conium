package com.github.cao.awa.conium.item.template.bedrock.glint

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrBoolean
import com.github.cao.awa.conium.template.ConiumTemplates.Item.GLINT
import com.google.gson.JsonElement
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumBedrockGlintTemplate(private val glint: Boolean) : ConiumItemTemplate(name = GLINT) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBedrockGlintTemplate = element.objectOrBoolean(
            {
                // Bedrock schema is:
                // "minecraft:glint": {
                //     "value": <bool>
                // }
                ConiumBedrockGlintTemplate(it["value"].asBoolean)
            },
            // Conium additional supporting schema:
            // "minecraft:glint": <bool>
            ::ConiumBedrockGlintTemplate
        )!!
    }

    // Set glint override.
    override fun settings(settings: Item.Settings) {
        settings.component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, this.glint)
    }
}
