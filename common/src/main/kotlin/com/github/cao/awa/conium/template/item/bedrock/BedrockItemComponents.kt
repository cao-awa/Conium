package com.github.cao.awa.conium.template.item.bedrock

import com.github.cao.awa.conium.item.template.bedrock.animation.BedrockUseAnimationComponent
import com.github.cao.awa.conium.item.template.bedrock.damage.BedrockDamageComponent
import com.github.cao.awa.conium.item.template.bedrock.destory.BedrockCanDestroyInCreativeComponent
import com.github.cao.awa.conium.item.template.bedrock.durability.BedrockDurabilityComponent
import com.github.cao.awa.conium.item.template.bedrock.food.BedrockFoodComponent
import com.github.cao.awa.conium.item.template.bedrock.fuel.BedrockFuelComponent
import com.github.cao.awa.conium.item.template.bedrock.glint.BedrockGlintComponent
import com.github.cao.awa.conium.item.template.bedrock.stack.size.BedrockMaxStackSizeComponent
import com.github.cao.awa.conium.item.template.bedrock.wearable.BedrockWearableComponent
import com.github.cao.awa.conium.item.template.rarity.ConiumRarityTemplate
import com.github.cao.awa.conium.template.ConiumTemplate

object BedrockItemComponents {
    // The food component in bedrock.
    const val FOOD: String = "minecraft:food"

    // The tool component in bedrock.
    const val DAMAGE: String = "minecraft:damage"
    const val DURABILITY: String = "minecraft:durability"

    // Bedrock stack size.
    const val MAX_STACK_SIZE: String = "minecraft:max_stack_size"

    // Can destroy in creative.
    const val CAN_DESTROY_IN_CREATIVE: String = "minecraft:can_destroy_in_creative"

    // Bedrock rarity.
    const val RARITY: String = "minecraft:rarity"

    // Bedrock fuel.
    const val FUEL: String = "minecraft:fuel"

    // Bedrock glint.
    const val GLINT: String = "minecraft:glint"

    // The food component in bedrock.
    const val WEARABLE: String = "minecraft:wearable"

    // Item use animation.
    const val USE_ANIMATION: String = "minecraft:use_animation"

    fun initBedrockItemComponents() {
        // Bedrock tool.
        ConiumTemplate.registerItem(
            DAMAGE,
            BedrockDamageComponent::create,
            true
        )
        ConiumTemplate.registerItem(
            DURABILITY,
            BedrockDurabilityComponent::create,
            true
        )

        // Stack.
        ConiumTemplate.registerItem(
            MAX_STACK_SIZE,
            BedrockMaxStackSizeComponent::create,
            true
        )

        // Can destroy in creative.
        ConiumTemplate.registerItem(
            CAN_DESTROY_IN_CREATIVE,
            BedrockCanDestroyInCreativeComponent::create,
            true
        )

        // Rarity.
        ConiumTemplate.registerItem(
            RARITY,
            ConiumRarityTemplate::createBedrock,
            true
        )

        // Food.
        ConiumTemplate.registerItem(
            FOOD,
            BedrockFoodComponent::create,
            true
        )

        // Fuel.
        ConiumTemplate.registerItem(
            FUEL,
            BedrockFuelComponent::create,
            true
        )

        // Glint.
        ConiumTemplate.registerItem(
            GLINT,
            BedrockGlintComponent::create,
            true
        )

        // Wearable.
        ConiumTemplate.registerItem(
            WEARABLE,
            BedrockWearableComponent::create,
            true
        )

        // Use animation.
        ConiumTemplate.registerItem(
            USE_ANIMATION,
            BedrockUseAnimationComponent::create,
            true
        )
    }
}