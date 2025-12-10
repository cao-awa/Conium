package com.github.cao.awa.conium.item.template.bedrock.durability

import com.github.cao.awa.conium.item.template.durability.ConiumDurabilityTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrInt
import com.github.cao.awa.conium.template.item.bedrock.BedrockItemComponents.DURABILITY
import com.google.gson.JsonElement

class BedrockDurabilityComponent(
    durability: Int,
    damageChance: IntRange = defaultChance
) : ConiumDurabilityTemplate(
    durability,
    damageChance,
    DURABILITY
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): BedrockDurabilityComponent = element.objectOrInt(
            {
                // Bedrock schema is:
                // "minecraft:durability": {
                //     "max_durability": <int>
                // }
                BedrockDurabilityComponent(it["max_durability"].asInt, createChance(it))
            },
            // Conium additional supporting schema:
            // "minecraft:durability": <int>
            ::BedrockDurabilityComponent
        )!!
    }
}
