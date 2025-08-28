package com.github.cao.awa.conium.block.template.bedrock.destructible

import com.github.cao.awa.conium.block.template.mining.ConiumHardnessTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrFloat
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockBlock.DESTRUCTIBLE_BY_MINING
import com.google.gson.JsonElement

object BedrockDestructibleByMiningComponent {
    @JvmStatic
    fun create(element: JsonElement): ConiumHardnessTemplate = element.objectOrFloat(
        {
            // Bedrock schema is:
            // "minecraft:destructible_by_mining": {
            //     "seconds_to_destroy": <float>
            // }
            ConiumHardnessTemplate(
                // Simple converting.
                it["seconds_to_destroy"].asFloat / 1.5F,
                DESTRUCTIBLE_BY_MINING
            )
        }
    ) {
        // Conium additional supporting schema:
        // "minecraft:destructible_by_mining": <float>
        ConiumHardnessTemplate(it, DESTRUCTIBLE_BY_MINING)
    }!!
}
