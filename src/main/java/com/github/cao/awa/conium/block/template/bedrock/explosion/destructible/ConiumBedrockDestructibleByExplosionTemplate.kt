package com.github.cao.awa.conium.block.template.bedrock.explosion.destructible

import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrFloat
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockBlock.DESTRUCTIBLE_BY_EXPLOSION
import com.google.gson.JsonElement
import net.minecraft.block.AbstractBlock
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumBedrockDestructibleByExplosionTemplate(private val explosionResistance: Float) : ConiumBlockTemplate(DESTRUCTIBLE_BY_EXPLOSION) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBedrockDestructibleByExplosionTemplate = element.objectOrFloat(
            {
                // Bedrock schema is:
                // "minecraft:destructible_by_explosion": {
                //     "explosion_resistance": <float>
                // }
                ConiumBedrockDestructibleByExplosionTemplate(
                    it["explosion_resistance"].asFloat
                )
            }
        ) {
            // Conium additional supporting schema:
            // "minecraft:destructible_by_explosion": <float>
            ConiumBedrockDestructibleByExplosionTemplate(it)
        }!!
    }

    override fun settings(settings: AbstractBlock.Settings) {
        // Set explosion resistance.
        settings.resistance(this.explosionResistance)
    }
}
