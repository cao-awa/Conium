package com.github.cao.awa.conium.item.component.display.name

import com.github.cao.awa.conium.item.template.display.name.ConiumDisplayNameTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrString
import com.github.cao.awa.conium.template.item.bedrock.BedrockItemComponents.DISPLAY_NAME
import com.google.gson.JsonElement

class BedrockDisplayNameComponent(
    name: String
) : ConiumDisplayNameTemplate(
    name,
    DISPLAY_NAME
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): BedrockDisplayNameComponent = element.objectOrString(
            {
                // Bedrock schema is:
                // "minecraft:display_name": {
                //     "value": <int>
                // }
                BedrockDisplayNameComponent(it["value"].asString)
            },
            // Conium additional supporting schema:
            // "minecraft:durability": <int>
            ::BedrockDisplayNameComponent
        )!!
    }
}
