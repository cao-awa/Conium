package com.github.cao.awa.conium.item.template.bedrock.fuel

import com.github.cao.awa.conium.item.template.fuel.ConiumFuelTemplate
import com.github.cao.awa.conium.kotlin.extend.json.objectOrFloat
import com.github.cao.awa.conium.template.ConiumTemplate.Companion.secondsToTicks
import com.google.gson.JsonElement

object BedrockFuelComponent {
    @JvmStatic
    fun create(element: JsonElement): ConiumFuelTemplate = element.objectOrFloat(
        {
            // Bedrock schema is:
            // "minecraft:fuel": {
            //     "duration": <float>
            // }
            ConiumFuelTemplate(secondsToTicks(it["duration"].asFloat))
        }
    ) {
        // Conium additional supporting schema:
        // "minecraft:fuel": <float>
        ConiumFuelTemplate(secondsToTicks(it))
    }!!
}
