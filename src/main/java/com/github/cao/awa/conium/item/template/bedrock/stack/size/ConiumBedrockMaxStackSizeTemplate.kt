package com.github.cao.awa.conium.item.template.bedrock.stack.size

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import net.minecraft.item.Item
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.util.Rarity

class ConiumBedrockMaxStackSizeTemplate(private val maxStackSize: Int) : ConiumItemTemplate(ConiumTemplates.BEDROCK_MAX_STACK_SIZE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBedrockMaxStackSizeTemplate {
            return if (element.isJsonObject) {
                // Bedrock schema is:
                // "minecraft:max_stack_size": {
                //     "value": <int>
                // }
                ConiumBedrockMaxStackSizeTemplate(validateStackSize(element.asJsonObject["value"].asInt))
            } else if (element.isJsonPrimitive) {
                // Conium additional supporting schema:
                // "minecraft:max_stack_size": <int>
                ConiumBedrockMaxStackSizeTemplate(validateStackSize(element.asInt))
            } else {
                throw IllegalArgumentException("Not supported syntax: $element")
            }
        }
    }

    override fun settings(settings: Item.Settings) {
        // Set max stack size.
        settings.maxCount(this.maxStackSize)
    }
}
