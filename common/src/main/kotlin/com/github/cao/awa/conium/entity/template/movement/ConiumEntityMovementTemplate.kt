package com.github.cao.awa.conium.entity.template.movement

import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.conium.template.entity.conium.ConiumEntityTemplates.MOVEMENT
import com.google.gson.JsonElement
import net.minecraft.world.entity.ai.attributes.Attributes

class ConiumEntityMovementTemplate(
    private val speed: Double,
    name: String = MOVEMENT
) : ConiumEntityTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, name: String = MOVEMENT): ConiumEntityMovementTemplate {
            val speed = if (element.isJsonPrimitive) {
                element.asDouble
            } else if (element.isJsonObject) {
                val obj = element.asJsonObject
                obj.get("value")?.asDouble ?: obj.get("speed")?.asDouble ?: 0.25
            } else {
                0.25
            }
            return ConiumEntityMovementTemplate(speed, name)
        }
    }

    override fun settings(settings: ConiumEntitySettings) {
        settings.attributes[Attributes.MOVEMENT_SPEED] = this.speed
    }
}
