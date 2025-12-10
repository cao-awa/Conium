package com.github.cao.awa.conium.template.entity.bedrock

import com.github.cao.awa.conium.entity.template.bedrock.collision.BedrockEntityCollisionBoxComponent
import com.github.cao.awa.conium.entity.template.bedrock.pushable.BedrockEntityPushableComponent
import com.github.cao.awa.conium.template.ConiumTemplate

object BedrockEntityComponents {
    // Collision box.
    const val COLLISION_BOX: String = "minecraft:collision_box"

    // Pushable.
    const val PUSHABLE: String = "minecraft:pushable"

    fun initBedrockEntityComponents() {
        // Collision box.
        ConiumTemplate.registerEntity(
            COLLISION_BOX,
            BedrockEntityCollisionBoxComponent::create,
            true
        )

        // Pushable.
        ConiumTemplate.registerEntity(
            PUSHABLE,
            BedrockEntityPushableComponent::create,
            true
        )
    }
}