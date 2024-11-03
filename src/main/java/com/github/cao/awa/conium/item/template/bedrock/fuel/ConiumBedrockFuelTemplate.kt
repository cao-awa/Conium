package com.github.cao.awa.conium.item.template.bedrock.fuel

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrFloat
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockItem.FUEL
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumBedrockFuelTemplate(private val duration: Int) : ConiumItemTemplate(FUEL) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBedrockFuelTemplate = element.objectOrFloat(
            {
                // Bedrock schema is:
                // "minecraft:fuel": {
                //     "duration": <float>
                // }
                ConiumBedrockFuelTemplate(toTicks(it["duration"].asFloat))
            }
        ) {
            // Conium additional supporting schema:
            // "minecraft:fuel": <float>
            ConiumBedrockFuelTemplate(toTicks(it))
        }!!

        // Attention to duration, this duration value in bedrock is seconds instead of ticks in bedrock.
        private fun toTicks(duration: Float): Int = (duration * 20).toInt()
    }

    override fun complete(item: ConiumItem) {
        // Add to fuel.
        Conium.coniumItemManager!!.addFuel(item, this.duration)
    }
}
