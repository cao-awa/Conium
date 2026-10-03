package com.github.cao.awa.conium.entity.template.bedrock.health

import com.github.cao.awa.conium.entity.template.health.ConiumEntityHealthTemplate
import com.github.cao.awa.conium.template.entity.bedrock.BedrockEntityComponents.HEALTH
import com.google.gson.JsonElement

object BedrockEntityHealthComponent {
    @JvmStatic
    fun create(element: JsonElement): ConiumEntityHealthTemplate = ConiumEntityHealthTemplate.create(element, HEALTH)
}
