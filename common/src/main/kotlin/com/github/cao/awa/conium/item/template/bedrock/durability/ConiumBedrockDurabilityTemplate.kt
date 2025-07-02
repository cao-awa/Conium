package com.github.cao.awa.conium.item.template.bedrock.durability

import com.github.cao.awa.conium.item.template.durability.ConiumDurabilityTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrInt
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockItem.DURABILITY
import com.google.gson.JsonElement

class ConiumBedrockDurabilityTemplate(
    durability: Int,
    damageChance: IntRange = defaultChance
) : ConiumDurabilityTemplate(
    durability,
    damageChance,
    DURABILITY
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumBedrockDurabilityTemplate = element.objectOrInt(
            {
                // Bedrock schema is:
                // "minecraft:durability": {
                //     "max_durability": <int>
                // }
                ConiumBedrockDurabilityTemplate(it["max_durability"].asInt, createChance(it))
            },
            // Conium additional supporting schema:
            // "minecraft:durability": <int>
            ::ConiumBedrockDurabilityTemplate
        )!!
    }
}
