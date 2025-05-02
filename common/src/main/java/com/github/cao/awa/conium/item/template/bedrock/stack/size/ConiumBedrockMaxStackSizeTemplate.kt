package com.github.cao.awa.conium.item.template.bedrock.stack.size

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrInt
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockItem.MAX_STACK_SIZE
import com.google.gson.JsonElement
import net.minecraft.item.Item
import net.minecraft.registry.RegistryWrapper

class ConiumBedrockMaxStackSizeTemplate(private val maxStackSize: Int) : ConiumItemTemplate(name = MAX_STACK_SIZE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumBedrockMaxStackSizeTemplate = element.objectOrInt(
            {
                // Bedrock schema is:
                // "minecraft:max_stack_size": {
                //     "value": <int>
                // }
                ConiumBedrockMaxStackSizeTemplate(validateStackSize(it["value"].asInt))
            },
        ) {
            // Conium additional supporting schema:
            // "minecraft:max_stack_size": <int>
            ConiumBedrockMaxStackSizeTemplate(validateStackSize(it))
        }!!
    }

    override fun settings(settings: Item.Settings) {
        // Set max stack size.
        settings.maxCount(this.maxStackSize)
    }
}
