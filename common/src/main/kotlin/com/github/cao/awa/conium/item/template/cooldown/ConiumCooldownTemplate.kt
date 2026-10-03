package com.github.cao.awa.conium.item.template.cooldown

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.COOLDOWN
import com.google.gson.JsonElement
import net.minecraft.world.item.Item

open class ConiumCooldownTemplate(private val duration: Float, name: String = COOLDOWN) : ConiumItemTemplate(name = name) {
    companion object {
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
            return ConiumCooldownTemplate(duration)
        }
    }

    override fun settings(settings: Item.Properties) {
        if (this.duration > 0.0F) {
            settings.useCooldown(this.duration)
        }
    }
}
