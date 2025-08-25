package com.github.cao.awa.conium.item.template.bedrock.glint

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extend.json.objectOrBoolean
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockItem.GLINT
import com.google.gson.JsonElement
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item

class BedrockGlintComponent(private val glint: Boolean) : ConiumItemTemplate(name = GLINT) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): BedrockGlintComponent = element.objectOrBoolean(
            {
                // Bedrock schema is:
                // "minecraft:glint": {
                //     "value": <bool>
                // }
                BedrockGlintComponent(it["value"].asBoolean)
            },
            // Conium additional supporting schema:
            // "minecraft:glint": <bool>
            ::BedrockGlintComponent
        )!!
    }

    // Set glint override.
    override fun settings(settings: Item.Settings) {
        settings.component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, this.glint)
    }
}
