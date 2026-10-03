package com.github.cao.awa.conium.entity.template.knockback

import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.conium.template.entity.conium.ConiumEntityTemplates.KNOCKBACK_RESISTANCE
import com.google.gson.JsonElement
import net.minecraft.world.entity.ai.attributes.Attributes

class ConiumEntityKnockbackResistanceTemplate(
    private val resistance: Double,
    name: String = KNOCKBACK_RESISTANCE
) : ConiumEntityTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, name: String = KNOCKBACK_RESISTANCE): ConiumEntityKnockbackResistanceTemplate {
            val res = if (element.isJsonPrimitive) {
                element.asDouble
            } else if (element.isJsonObject) {
                val obj = element.asJsonObject
                obj.get("value")?.asDouble ?: 0.0
            } else {
                0.0
            }
            return ConiumEntityKnockbackResistanceTemplate(res, name)
        }
    }

    override fun settings(settings: ConiumEntitySettings) {
        settings.attributes[Attributes.KNOCKBACK_RESISTANCE] = this.resistance
    }
}
