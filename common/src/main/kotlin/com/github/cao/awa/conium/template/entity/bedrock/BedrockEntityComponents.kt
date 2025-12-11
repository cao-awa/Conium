package com.github.cao.awa.conium.template.entity.bedrock

import com.github.cao.awa.conium.entity.template.bedrock.collision.BedrockEntityCollisionBoxComponent
import com.github.cao.awa.conium.entity.template.bedrock.pushable.BedrockEntityPushableComponent
import com.github.cao.awa.conium.template.ConiumTemplate

/**
 * Bedrock entity components register.
 *
 * Ordering with bedrock.dev:
 * https://bedrock.dev/docs/stable/Entities
 *
 * @author cao_awa
 * @author 草二号机
 *
 * @since 1.0.0
 */
object BedrockEntityComponents {
    const val COLLISION_BOX: String = "minecraft:collision_box"
    const val PUSHABLE: String = "minecraft:pushable"

    fun initBedrockEntityComponents() {
        ConiumTemplate.registerEntity(
            COLLISION_BOX,
            BedrockEntityCollisionBoxComponent::create,
            true
        )

        ConiumTemplate.registerEntity(
            PUSHABLE,
            BedrockEntityPushableComponent::create,
            true
        )
    }
}