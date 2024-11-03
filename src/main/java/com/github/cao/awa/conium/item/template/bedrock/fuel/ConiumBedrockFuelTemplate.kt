package com.github.cao.awa.conium.item.template.bedrock.fuel

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockItem.FUEL
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumBedrockFuelTemplate(private val duration: Int) : ConiumItemTemplate(FUEL) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBedrockFuelTemplate {
            // Attention to duration, this value is seconds instead of ticks in bedrock.
            return if (element.isJsonObject) {
                // Bedrock schema is:
                // "minecraft:fuel": {
                //     "duration": <float>
                // }
                ConiumBedrockFuelTemplate(toTicks(element.asJsonObject["duration"].asFloat))
            } else if (element.isJsonPrimitive) {
                // Conium additional supporting schema:
                // "minecraft:fuel": <float>
                ConiumBedrockFuelTemplate(toTicks(element.asFloat))
            } else {
                throw IllegalArgumentException("Not supported syntax: $element")
            }
        }

        private fun toTicks(seconds: Float): Int = (seconds * 20).toInt()
    }

    override fun complete(item: ConiumItem) {
        Conium.coniumItemManager!!.addFuel(item, this.duration)
    }
}
