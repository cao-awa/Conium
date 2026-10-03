package com.github.cao.awa.conium.item.component.fire

import com.github.cao.awa.conium.item.template.fire.ConiumFireResistantTemplate
import com.github.cao.awa.conium.template.item.bedrock.BedrockItemComponents.FIRE_RESISTANT
import com.google.gson.JsonElement

object BedrockFireResistantComponent {
    @JvmStatic
    fun create(element: JsonElement): ConiumFireResistantTemplate {
        val enabled = if (element.isJsonPrimitive) {
            element.asBoolean
        } else if (element.isJsonObject) {
            element.asJsonObject.get("value")?.asBoolean ?: true
        } else {
            true
        }
        return ConiumFireResistantTemplate(enabled, FIRE_RESISTANT)
    }
}
