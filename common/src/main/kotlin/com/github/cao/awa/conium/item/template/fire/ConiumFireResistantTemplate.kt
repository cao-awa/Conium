package com.github.cao.awa.conium.item.template.fire

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.FIRE_RESISTANT
import com.google.gson.JsonElement
import net.minecraft.world.item.Item

open class ConiumFireResistantTemplate(private val fireResistant: Boolean = true, name: String = FIRE_RESISTANT) : ConiumItemTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumFireResistantTemplate {
            val enabled = if (element.isJsonPrimitive) {
                element.asBoolean
            } else if (element.isJsonObject) {
                element.asJsonObject.get("value")?.asBoolean ?: true
            } else {
                true
            }
            return ConiumFireResistantTemplate(enabled)
        }
    }

    override fun settings(settings: Item.Properties) {
        if (this.fireResistant) {
            settings.fireResistant()
        }
    }
}
