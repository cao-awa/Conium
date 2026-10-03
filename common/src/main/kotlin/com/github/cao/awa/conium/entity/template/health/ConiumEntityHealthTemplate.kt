package com.github.cao.awa.conium.entity.template.health

import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.conium.template.entity.conium.ConiumEntityTemplates.HEALTH
import com.google.gson.JsonElement
import net.minecraft.world.entity.ai.attributes.Attributes

class ConiumEntityHealthTemplate(
    private val maxHealth: Double,
    name: String = HEALTH
) : ConiumEntityTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, name: String = HEALTH): ConiumEntityHealthTemplate {
            val max = if (element.isJsonPrimitive) {
                element.asDouble
            } else if (element.isJsonObject) {
                val obj = element.asJsonObject
                obj.get("max")?.asDouble ?: obj.get("value")?.asDouble ?: 20.0
            } else {
                20.0
            }
            return ConiumEntityHealthTemplate(max, name)
        }
    }

    override fun settings(settings: ConiumEntitySettings) {
        settings.attributes[Attributes.MAX_HEALTH] = this.maxHealth
    }
}
