package com.github.cao.awa.conium.entity.template.bedrock.fire

import com.github.cao.awa.conium.entity.template.fire.ConiumEntityFireImmuneTemplate
import com.github.cao.awa.conium.template.entity.bedrock.BedrockEntityComponents.FIRE_IMMUNE
import com.google.gson.JsonElement

object BedrockEntityFireImmuneComponent {
    @JvmStatic
    fun create(element: JsonElement): ConiumEntityFireImmuneTemplate = ConiumEntityFireImmuneTemplate.create(element, FIRE_IMMUNE)
}
