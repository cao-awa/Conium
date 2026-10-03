package com.github.cao.awa.conium.item.component.cooldown

import com.github.cao.awa.conium.item.template.cooldown.ConiumCooldownTemplate
import com.github.cao.awa.conium.template.item.bedrock.BedrockItemComponents.COOLDOWN
import com.google.gson.JsonElement

object BedrockCooldownComponent {
    @JvmStatic
    fun create(element: JsonElement): ConiumCooldownTemplate {
        val duration = if (element.isJsonPrimitive) {
            element.asFloat
        } else if (element.isJsonObject) {
            element.asJsonObject.get("duration")?.asFloat
                ?: element.asJsonObject.get("value")?.asFloat
                ?: 1.0F
        } else {
            1.0F
        }
        return ConiumCooldownTemplate(duration, COOLDOWN)
    }
}
