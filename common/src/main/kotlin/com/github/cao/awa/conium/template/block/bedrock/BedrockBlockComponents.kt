package com.github.cao.awa.conium.template.block.bedrock

import com.github.cao.awa.conium.block.template.bedrock.collision.BedrockBlockCollisionBoxComponent
import com.github.cao.awa.conium.block.template.bedrock.destructible.BedrockDestructibleByMiningComponent
import com.github.cao.awa.conium.block.template.bedrock.destructible.explosion.BedrockDestructibleByExplosionComponent
import com.github.cao.awa.conium.block.template.bedrock.light.BedrockLightEmissionComponent
import com.github.cao.awa.conium.block.template.map.ConiumBedrockMapColorTemplate
import com.github.cao.awa.conium.template.ConiumTemplate

/**
 * Bedrock block components register.
 *
 * Ordering with bedrock wiki:
 * https://wiki.bedrock.dev/blocks/block-components
 *
 * @author cao_awa
 * @author 草二号机
 *
 * @since 1.0.0
 */
object BedrockBlockComponents {
    const val COLLISION_BOX: String = "minecraft:collision_box"
    const val CRAFTING_TABLE: String = "minecraft:crafting_table"
    const val DESTRUCTIBLE_BY_EXPLOSION: String = "minecraft:destructible_by_explosion"
    const val DESTRUCTIBLE_BY_MINING: String = "minecraft:destructible_by_mining"
    const val DESTRUCTION_PARTICLES: String = "minecraft:destruction_particles"
    const val DISPLAY_NAME: String = "minecraft:display_name"
    const val EMBEDDED_VISUAL: String = "minecraft:embedded_visual"
    const val ENTITY_FALL_ON: String = "minecraft:entity_fall_on"
    const val FLAMMABLE: String = "minecraft:flammable"
    const val FLOWER_POTTABLE: String = "minecraft:flower_pottable"
    const val FRICTION: String = "minecraft:friction"
    const val GEOMETRY: String = "minecraft:geometry"
    const val ITEM_VISUAL: String = "minecraft:item_visual"
    const val LIGHT_DAMPENING: String = "minecraft:light_dampening"
    const val LIGHT_EMISSION: String = "minecraft:light_emission"
    const val LIQUID_DETECTION: String = "minecraft:liquid_detection"
    const val LOOT: String = "minecraft:loot"
    const val MAP_COLOR: String = "minecraft:map_color"
    const val MATERIAL_INSTANCE: String = "minecraft:material_instances"
    const val MOVABLE: String = "minecraft:movable"
    const val REPLACEMENT_FILTER: String = "minecraft:placement_filter"
    const val PRECIPITATION_INTERACTIONS: String = "minecraft:precipitation_interactions"
    const val RANDOM_OFFSET: String = "minecraft:random_offset"
    const val REDSTONE_CONDUCTIVITY: String = "minecraft:redstone_conductivity"
    const val REDSTONE_PRODUCER: String = "minecraft:redstone_producer"
    const val REPLACEABLE: String = "minecraft:replaceable"
    const val SELECTION_BOX: String = "minecraft:selection_box"
    const val TICK: String = "minecraft:tick"
    const val TRANSFORMATION: String = "minecraft:transformation"

    fun initBedrockBlockComponents() {
        ConiumTemplate.registerBlock(
            COLLISION_BOX,
            BedrockBlockCollisionBoxComponent::create,
            true
        )

        ConiumTemplate.registerBlock(
            DESTRUCTIBLE_BY_EXPLOSION,
            BedrockDestructibleByExplosionComponent::create,
            true
        )

        ConiumTemplate.registerBlock(
            DESTRUCTIBLE_BY_MINING,
            BedrockDestructibleByMiningComponent::create,
            true
        )

        ConiumTemplate.registerBlock(
            LIGHT_EMISSION,
            BedrockLightEmissionComponent::create,
            true
        )

        ConiumTemplate.registerBlock(
            MAP_COLOR,
            ConiumBedrockMapColorTemplate::create,
            true
        )
    }
}