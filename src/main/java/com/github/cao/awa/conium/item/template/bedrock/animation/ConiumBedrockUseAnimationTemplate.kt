package com.github.cao.awa.conium.item.template.bedrock.animation

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.component.withComponentProvides
import com.github.cao.awa.conium.kotlin.extent.component.withComputeUseAction
import com.github.cao.awa.conium.kotlin.extent.component.withCreateConsumable
import com.github.cao.awa.conium.kotlin.extent.item.components
import com.github.cao.awa.conium.kotlin.extent.json.objectOrString
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockItem.USE_ANIMATION
import com.google.gson.JsonElement
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item
import net.minecraft.item.consume.UseAction
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumBedrockUseAnimationTemplate(private val useAction: UseAction) : ConiumItemTemplate(true, USE_ANIMATION) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBedrockUseAnimationTemplate = element.objectOrString(
            {
                // Bedrock schema is:
                // "minecraft:use_animation": {
                //     "value": <string>
                // }
                ConiumBedrockUseAnimationTemplate(createUseAction(it["value"].asString))
            }
        ) {
            // Conium additional supporting schema:
            // "minecraft:use_animation": <string>
            ConiumBedrockUseAnimationTemplate(createUseAction(it))
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
