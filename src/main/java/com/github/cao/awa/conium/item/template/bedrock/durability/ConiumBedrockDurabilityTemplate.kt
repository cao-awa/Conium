package com.github.cao.awa.conium.item.template.bedrock.durability

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.component.withComponent
import com.github.cao.awa.conium.kotlin.extent.component.withComputeTool
import com.github.cao.awa.conium.kotlin.extent.component.withCreateTool
import com.github.cao.awa.conium.kotlin.extent.item.components
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumBedrockDurabilityTemplate(private val durability: Int) : ConiumItemTemplate(ConiumTemplates.BEDROCK_DURABILITY) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBedrockDurabilityTemplate {
            return if (element.isJsonObject) {
                // Bedrock schema is:
                // "minecraft:durability": {
                //     "max_durability": <int>
                // }
                ConiumBedrockDurabilityTemplate(element.asJsonObject["max_durability"].asInt)
            } else if (element.isJsonPrimitive) {
                // Conium additional supporting schema:
                // "minecraft:durability": <int>
                ConiumBedrockDurabilityTemplate(element.asInt)
            } else {
                throw IllegalArgumentException("Not supported syntax: $element")
            }
        }
    }

    override fun attach(item: ConiumItem) {

    }

    override fun complete(item: ConiumItem) {
        // Should increments 'USED' stat when an item has durability.
        item.shouldPostHit = true
    }

    override fun settings(settings: Item.Settings) {
        // Set max durability.
        settings.maxDamage(this.durability)

        // Create default tool component, let it can be damage durability when breaking block.
        settings.components.withComponent(DataComponentTypes.TOOL, withCreateTool(), withComputeTool())
    }
}
