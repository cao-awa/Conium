package com.github.cao.awa.conium.item.component.compostable

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrFloat
import com.github.cao.awa.conium.mixin.block.compostable.ComposterBlockAccessor
import com.github.cao.awa.conium.template.item.bedrock.BedrockItemComponents.COMPOSTABLE
import com.google.gson.JsonElement

class BedrockCompostableComponent(private val chance: Float) : ConiumItemTemplate(true, COMPOSTABLE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): BedrockCompostableComponent = element.objectOrFloat(
            {
                // Bedrock schema is:
                // "minecraft:compostable": {
                //     "value": <float>
                // }
                BedrockCompostableComponent(it["composting_chance"].asFloat)
            }
        ) {
            // Conium additional supporting schema:
            // "minecraft:compostable": <float>
            BedrockCompostableComponent(it)
        }!!
    }

    override fun complete(target: ConiumItem) {
        ComposterBlockAccessor.invokeRegisterCompostableItem(
            this.chance,
            target
        )
    }
}
