package com.github.cao.awa.conium.item.component.animation

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.component.withComponentProvides
import com.github.cao.awa.conium.kotlin.extent.component.withComputeUseAction
import com.github.cao.awa.conium.kotlin.extent.component.withCreateConsumable
import com.github.cao.awa.conium.kotlin.extent.item.components
import com.github.cao.awa.conium.kotlin.extent.json.objectOrString
import com.github.cao.awa.conium.template.item.bedrock.BedrockItemComponents.USE_ANIMATION
import com.google.gson.JsonElement
import net.minecraft.core.component.DataComponents
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemUseAnimation

class BedrockUseAnimationComponent(private val useAction: ItemUseAnimation) : ConiumItemTemplate(true, USE_ANIMATION) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): BedrockUseAnimationComponent = element.objectOrString(
            {
                // Bedrock schema is:
                // "minecraft:use_animation": {
                //     "value": <string>
                // }
                BedrockUseAnimationComponent(createUseAction(it["value"].asString))
            }
        ) {
            // Conium additional supporting schema:
            // "minecraft:use_animation": <string>
            BedrockUseAnimationComponent(createUseAction(it))
        }!!
    }

    override fun settings(settings: Item.Properties) {
        settings.components.withComponentProvides(
            DataComponents.CONSUMABLE,
            withCreateConsumable(),
            withComputeUseAction(),
            ::useAction
        )
    }
}
