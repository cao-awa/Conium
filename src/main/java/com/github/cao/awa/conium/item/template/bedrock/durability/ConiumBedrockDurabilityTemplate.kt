package com.github.cao.awa.conium.item.template.bedrock.durability

import com.github.cao.awa.conium.item.template.durability.ConiumDurabilityTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockItem.DURABILITY
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumBedrockDurabilityTemplate(
    durability: Int,
    damageChance: IntRange
) : ConiumDurabilityTemplate(
    DURABILITY,
    durability,
    damageChance
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBedrockDurabilityTemplate {
            return if (element.isJsonObject) {
                (element as JsonObject).let {
                    // Bedrock schema is:
                    // "minecraft:durability": {
                    //     "max_durability": <int>
                    // }
                    ConiumBedrockDurabilityTemplate(
                        it["max_durability"].asInt,
                        createChance(element)
                    )
                }
            } else if (element.isJsonPrimitive) {
                // Conium additional supporting schema:
                // "minecraft:durability": <int>
                ConiumBedrockDurabilityTemplate(element.asInt, defaultChance)
            } else {
                throw IllegalArgumentException("Not supported syntax: $element")
            }
        }
    }
}
