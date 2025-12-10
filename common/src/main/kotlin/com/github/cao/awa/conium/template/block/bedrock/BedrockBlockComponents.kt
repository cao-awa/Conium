package com.github.cao.awa.conium.template.block.bedrock

import com.github.cao.awa.conium.block.template.bedrock.collision.BedrockBlockCollisionBoxComponent
import com.github.cao.awa.conium.block.template.bedrock.destructible.BedrockDestructibleByMiningComponent
import com.github.cao.awa.conium.block.template.bedrock.destructible.explosion.BedrockDestructibleByExplosionComponent
import com.github.cao.awa.conium.block.template.bedrock.light.BedrockLightEmissionComponent
import com.github.cao.awa.conium.block.template.map.ConiumBedrockMapColorTemplate
import com.github.cao.awa.conium.template.ConiumTemplate

object BedrockBlockComponents {
    // Destructible.
    const val DESTRUCTIBLE_BY_EXPLOSION: String = "minecraft:destructible_by_explosion"
    const val DESTRUCTIBLE_BY_MINING: String = "minecraft:destructible_by_mining"

    // Map color.
    const val MAP_COLOR: String = "minecraft:map_color"

    // Light emission.
    const val LIGHT_EMISSION: String = "minecraft:light_emission"

    // Collision.
    const val COLLISION_BOX: String = "minecraft:collision_box"

    fun initBedrockBlockComponents() {
        // Destructible by explosion.
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

        // Map color.
        ConiumTemplate.registerBlock(
            MAP_COLOR,
            ConiumBedrockMapColorTemplate::create,
            true
        )

        // Light emission.
        ConiumTemplate.registerBlock(
            LIGHT_EMISSION,
            BedrockLightEmissionComponent::create,
            true
        )

        // Collision.
        ConiumTemplate.registerBlock(
            COLLISION_BOX,
            BedrockBlockCollisionBoxComponent::create,
            true
        )
    }
}