package com.github.cao.awa.conium.item.template.bedrock.animation

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extend.component.withComponentProvides
import com.github.cao.awa.conium.kotlin.extend.component.withComputeUseAction
import com.github.cao.awa.conium.kotlin.extend.component.withCreateConsumable
import com.github.cao.awa.conium.kotlin.extend.item.components
import com.github.cao.awa.conium.kotlin.extend.json.objectOrString
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockItem.USE_ANIMATION
import com.google.gson.JsonElement
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item
import net.minecraft.item.consume.UseAction

class BedrockUseAnimationComponent(private val useAction: UseAction) : ConiumItemTemplate(true, USE_ANIMATION) {
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

    override fun settings(settings: Item.Settings) {
        settings.components.withComponentProvides(
            DataComponentTypes.CONSUMABLE,
            withCreateConsumable(),
            withComputeUseAction(),
            ::useAction
        )
    }
}
