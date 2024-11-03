package com.github.cao.awa.conium.block.template.bedrock.explosion.destructible

import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockBlock.DESTRUCTIBLE_BY_EXPLOSION
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.block.AbstractBlock
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumBedrockDestructibleByExplosionTemplate(private val explosionResistance: Float): ConiumBlockTemplate(DESTRUCTIBLE_BY_EXPLOSION) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBedrockDestructibleByExplosionTemplate {
            return if (element.isJsonObject) {
                (element as JsonObject).let {
                    // Bedrock schema is:
                    // "minecraft:destructible_by_explosion": {
                    //     "explosion_resistance": <float>
                    // }
                    ConiumBedrockDestructibleByExplosionTemplate(
                        it["explosion_resistance"].asFloat
                    )
                }
            } else if (element.isJsonPrimitive) {
                // Conium additional supporting schema:
                // "minecraft:destructible_by_explosion": <float>
                ConiumBedrockDestructibleByExplosionTemplate(element.asFloat)
            } else {
                throw IllegalArgumentException("Not supported syntax: $element")
            }
        }
    }

    override fun settings(settings: AbstractBlock.Settings) {
        // Set explosion resistance.
        settings.resistance(this.explosionResistance)
    }
}
