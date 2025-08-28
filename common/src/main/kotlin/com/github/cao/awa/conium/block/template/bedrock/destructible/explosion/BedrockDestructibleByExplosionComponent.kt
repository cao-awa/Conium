package com.github.cao.awa.conium.block.template.bedrock.destructible.explosion

import com.github.cao.awa.conium.block.template.explosion.resistance.ConiumExplosionResistanceTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrFloat
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockBlock.DESTRUCTIBLE_BY_EXPLOSION
import com.google.gson.JsonElement

class BedrockDestructibleByExplosionComponent(explosionResistance: Float) : ConiumExplosionResistanceTemplate(explosionResistance, DESTRUCTIBLE_BY_EXPLOSION) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): BedrockDestructibleByExplosionComponent = element.objectOrFloat(
            {
                // Bedrock schema is:
                // "minecraft:destructible_by_explosion": {
                //     "explosion_resistance": <float>
                // }
                BedrockDestructibleByExplosionComponent(
                    it["explosion_resistance"].asFloat
                )
            }
        ) {
            // Conium additional supporting schema:
            // "minecraft:destructible_by_explosion": <float>
            BedrockDestructibleByExplosionComponent(it)
        }!!
    }
}
