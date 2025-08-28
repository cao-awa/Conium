package com.github.cao.awa.conium.entity.template.bedrock.pushable

import com.github.cao.awa.conium.entity.template.pushable.ConiumEntityPushableTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockEntity.PUSHABLE
import com.google.gson.JsonElement

object BedrockEntityPushableComponent {
    @JvmStatic
    fun create(element: JsonElement): ConiumEntityPushableTemplate = ConiumEntityPushableTemplate.create(element, PUSHABLE)
}
