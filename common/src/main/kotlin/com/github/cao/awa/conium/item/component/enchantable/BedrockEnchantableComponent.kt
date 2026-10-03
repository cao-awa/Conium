package com.github.cao.awa.conium.item.component.enchantable

import com.github.cao.awa.conium.item.template.enchantable.ConiumEnchantableTemplate
import com.github.cao.awa.conium.template.item.bedrock.BedrockItemComponents.ENCHANTABLE
import com.google.gson.JsonElement

object BedrockEnchantableComponent {
    @JvmStatic
    fun create(element: JsonElement): ConiumEnchantableTemplate {
        val value = if (element.isJsonPrimitive) {
            element.asInt
        } else if (element.isJsonObject) {
            element.asJsonObject.get("value")?.asInt ?: 1
        } else {
            1
        }
        return ConiumEnchantableTemplate(value, ENCHANTABLE)
    }
}
