package com.github.cao.awa.conium.entity.template

import com.github.cao.awa.conium.entity.ConiumEntity
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettingsWithTypeBuilder
import com.github.cao.awa.conium.template.ConiumTemplate
import net.minecraft.entity.EntityType

abstract class ConiumEntityTemplate(isClient: Boolean = false, name: String) : ConiumTemplate<ConiumEntity, ConiumEntitySettingsWithTypeBuilder>(isClient, name) {
    override fun attach(target: ConiumEntity) {
        // Do nothing.
    }

    override fun complete(target: ConiumEntity) {
        // Do nothing.
    }

    override fun prepare(target: ConiumEntitySettingsWithTypeBuilder) {
        type(target.builder)
        settings(target.settings)
    }

    open fun type(type: EntityType.Builder<ConiumEntity>) {
        // Do nothing.
    }

    open fun settings(settings: ConiumEntitySettings) {
        // Do nothing.
    }
}
