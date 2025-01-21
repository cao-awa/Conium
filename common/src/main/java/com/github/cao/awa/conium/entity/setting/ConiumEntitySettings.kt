package com.github.cao.awa.conium.entity.setting

import com.github.cao.awa.conium.entity.ConiumEntity
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import net.minecraft.entity.EntityType

class ConiumEntitySettings : ConiumAbstractEntitySettings<ConiumEntitySettings>() {
    companion object {
        @JvmStatic
        fun create(templates: MutableList<ConiumEntityTemplate>, type: EntityType.Builder<ConiumEntity>): ConiumEntitySettings {
            return ConiumEntitySettings().also {
                templates.forEach { template ->
                    template.prepare(ConiumEntitySettingsWithTypeBuilder(type, it))
                }
            }
        }
    }

    override fun newInstance(): ConiumEntitySettings = ConiumEntitySettings()
}
