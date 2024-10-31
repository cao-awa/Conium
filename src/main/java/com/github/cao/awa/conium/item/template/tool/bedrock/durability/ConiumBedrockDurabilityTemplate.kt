package com.github.cao.awa.conium.item.template.tool.bedrock.durability

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.component.withComponent
import com.github.cao.awa.conium.kotlin.extent.component.withComputeTool
import com.github.cao.awa.conium.kotlin.extent.component.withCreateTool
import com.github.cao.awa.conium.kotlin.extent.item.components
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumBedrockDurabilityTemplate(private val durability: Int) : ConiumItemTemplate(ConiumTemplates.BEDROCK_DURABILITY) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBedrockDurabilityTemplate {
            if (element is JsonObject) {
                return ConiumBedrockDurabilityTemplate(element["max_durability"].asInt)
            }
            throw IllegalArgumentException("Not supported syntax: $element")
        }
    }

    override fun attach(item: ConiumItem) {

    }

    override fun complete(item: ConiumItem) {

    }

    override fun settings(settings: Item.Settings) {
        // Set max durability.
        settings.maxDamage(this.durability)

        // Create default tool component, let it can be damage durability when breaking block.
        settings.components.withComponent(DataComponentTypes.TOOL, withCreateTool(), withComputeTool())
    }
}
