package com.github.cao.awa.conium.template.item.bedrock

import com.github.cao.awa.conium.item.component.animation.BedrockUseAnimationComponent
import com.github.cao.awa.conium.item.component.damage.BedrockDamageComponent
import com.github.cao.awa.conium.item.component.destory.BedrockCanDestroyInCreativeComponent
import com.github.cao.awa.conium.item.component.durability.BedrockDurabilityComponent
import com.github.cao.awa.conium.item.component.entity.placer.BedrockEntityPlacerComponent
import com.github.cao.awa.conium.item.component.food.BedrockFoodComponent
import com.github.cao.awa.conium.item.component.fuel.BedrockFuelComponent
import com.github.cao.awa.conium.item.component.glint.BedrockGlintComponent
import com.github.cao.awa.conium.item.component.stack.size.BedrockMaxStackSizeComponent
import com.github.cao.awa.conium.item.component.wearable.BedrockWearableComponent
import com.github.cao.awa.conium.item.template.rarity.ConiumRarityTemplate
import com.github.cao.awa.conium.template.ConiumTemplate

/**
 * Bedrock components register.
 *
 * Ordering with bedrock wiki:
 * https://wiki.bedrock.dev/items/item-components
 *
 * @author cao_awa
 * @author 草二号机
 *
 * @since 1.0.0
 */
object BedrockItemComponents {
    const val ALLOW_OFF_HAND: String = "minecraft:allow_of_hand"
    const val BLOCK_PLACER: String = "minecraft:block_placer"
    const val BUNDLE_INTERACTION: String = "minecraft:bundle_interaction"
    const val CAN_DESTROY_IN_CREATIVE: String = "minecraft:can_destroy_in_creative"
    const val COMPOSTABLE: String = "minecraft:compostable"
    const val COOLDOWN: String = "minecraft:cooldown"
    const val DAMAGE: String = "minecraft:damage"
    const val DAMAGE_ABSORPTION: String = "minecraft:damage_absorption"
    const val DIGGER: String = "minecraft:digger"
    const val DISPLAY_NAME: String = "minecraft:display_name"
    const val DURABILITY: String = "minecraft:durability"
    const val DURABILITY_SENSOR: String = "minecraft:durability_sensor"
    const val DYEABLE: String = "minecraft:dyeable"
    const val ENCHANTABLE: String = "minecraft:enchantable"
    const val ENTITY_PLACER: String = "minecraft:entity_placer"
    const val FIRE_RESISTANCE: String = "minecraft:fire_resistant"
    const val FOOD: String = "minecraft:food"
    const val FUEL: String = "minecraft:fuel"
    const val GLINT: String = "minecraft:glint"
    const val HAND_EQUIPPED: String = "minecraft:hand_equipped"
    const val HOVER_TEXT_COLOR: String = "minecraft:hover_text_color"
    const val ICON: String = "minecraft:icon"
    const val INTERACT_BUTTON: String = "minecraft:interact_button"
    const val KINETIC_WEAPON: String = "minecraft:kinetic_weapon"
    const val MAX_STACK_SIZE: String = "minecraft:max_stack_size"
    const val LIQUID_CLIPPED: String = "minecraft:liquid_clipped"
    const val PIERCING_WEAPON: String = "minecraft:piercing_weapon"
    const val PROJECTILE: String = "minecraft:projectile"
    const val RARITY: String = "minecraft:rarity"
    const val RECORD: String = "minecraft:record"
    const val REPAIRABLE: String = "minecraft:repairable"
    const val SHOOTER: String = "minecraft:shooter"
    const val SHOULD_DESPAWN: String = "minecraft:should_despawn"
    const val STORAGE_ITEM: String = "minecraft:storage_item"
    const val STORAGE_WEIGHT_LIMIT: String = "minecraft:storage_weight_limit"
    const val STORAGE_WEIGHT_MODIFIER: String = "minecraft:storage_weight_modifier"
    const val SWING_DURATION: String = "minecraft:swing_duration"
    const val SWING_SOUNDS: String = "minecraft:swing_sounds"
    const val TAGS: String = "minecraft:tags"
    const val THROWABLE: String = "minecraft:throwable"
    const val USE_ANIMATION: String = "minecraft:use_animation"
    const val USE_MODIFIERS: String = "minecraft:use_modifiers"
    const val WEARABLE: String = "minecraft:wearable"

    fun initBedrockItemComponents() {
        // Can destroy in creative.
        ConiumTemplate.registerItem(
            CAN_DESTROY_IN_CREATIVE,
            BedrockCanDestroyInCreativeComponent::create,
            true
        )

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

        ConiumTemplate.registerItem(
            ENTITY_PLACER,
            BedrockEntityPlacerComponent::create,
            true
        )

        ConiumTemplate.registerItem(
            FOOD,
            BedrockFoodComponent::create,
            true
        )

        ConiumTemplate.registerItem(
            FUEL,
            BedrockFuelComponent::create,
            true
        )

        ConiumTemplate.registerItem(
            GLINT,
            BedrockGlintComponent::create,
            true
        )

        ConiumTemplate.registerItem(
            MAX_STACK_SIZE,
            BedrockMaxStackSizeComponent::create,
            true
        )

        ConiumTemplate.registerItem(
            RARITY,
            ConiumRarityTemplate::createBedrock,
            true
        )

        ConiumTemplate.registerItem(
            USE_ANIMATION,
            BedrockUseAnimationComponent::create,
            true
        )

        ConiumTemplate.registerItem(
            WEARABLE,
            BedrockWearableComponent::create,
            true
        )
    }
}