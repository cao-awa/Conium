package com.github.cao.awa.conium.entity.template.fire

import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrBoolean
import com.github.cao.awa.conium.template.entity.conium.ConiumEntityTemplates.FIRE_IMMUNE
import com.google.gson.JsonElement

class ConiumEntityFireImmuneTemplate(
    private val fireImmune: Boolean,
    name: String = FIRE_IMMUNE
) : ConiumEntityTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, name: String = FIRE_IMMUNE): ConiumEntityFireImmuneTemplate = element.objectOrBoolean(
            {
                ConiumEntityFireImmuneTemplate(
                    it["value"]?.asBoolean ?: true,
                    name
                )
            }
        ) {
            ConiumEntityFireImmuneTemplate(it, name)
        } ?: ConiumEntityFireImmuneTemplate(true, name)
    }

    override fun settings(settings: ConiumEntitySettings) {
        settings.fireImmune = this.fireImmune
    }
}
