package com.github.cao.awa.conium.item.component.stack.size

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrInt
import com.github.cao.awa.conium.template.item.bedrock.BedrockItemComponents.MAX_STACK_SIZE
import com.google.gson.JsonElement
import net.minecraft.item.Item

class BedrockMaxStackSizeComponent(private val maxStackSize: Int) : ConiumItemTemplate(name = MAX_STACK_SIZE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): BedrockMaxStackSizeComponent = element.objectOrInt(
            {
                // Bedrock schema is:
                // "minecraft:max_stack_size": {
                //     "value": <int>
                // }
                BedrockMaxStackSizeComponent(validateStackSize(it["value"].asInt))
            },
        ) {
            // Conium additional supporting schema:
            // "minecraft:max_stack_size": <int>
            BedrockMaxStackSizeComponent(validateStackSize(it))
        }!!
    }

    override fun settings(settings: Item.Settings) {
        // Set max stack size.
        settings.maxCount(this.maxStackSize)
    }
}
