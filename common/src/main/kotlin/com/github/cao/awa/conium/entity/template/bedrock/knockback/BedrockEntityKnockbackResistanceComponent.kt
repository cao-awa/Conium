package com.github.cao.awa.conium.entity.template.bedrock.knockback

import com.github.cao.awa.conium.entity.template.knockback.ConiumEntityKnockbackResistanceTemplate
import com.github.cao.awa.conium.template.entity.bedrock.BedrockEntityComponents.KNOCKBACK_RESISTANCE
import com.google.gson.JsonElement

object BedrockEntityKnockbackResistanceComponent {
    @JvmStatic
    fun create(element: JsonElement): ConiumEntityKnockbackResistanceTemplate = ConiumEntityKnockbackResistanceTemplate.create(element, KNOCKBACK_RESISTANCE)
}
