package com.github.cao.awa.conium.entity.template.bedrock.movement

import com.github.cao.awa.conium.entity.template.movement.ConiumEntityMovementTemplate
import com.github.cao.awa.conium.template.entity.bedrock.BedrockEntityComponents.MOVEMENT
import com.google.gson.JsonElement

object BedrockEntityMovementComponent {
    @JvmStatic
    fun create(element: JsonElement): ConiumEntityMovementTemplate = ConiumEntityMovementTemplate.create(element, MOVEMENT)
}
