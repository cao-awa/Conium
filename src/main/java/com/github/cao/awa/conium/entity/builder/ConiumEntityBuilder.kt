package com.github.cao.awa.conium.entity.builder

import com.github.cao.awa.conium.entity.ConiumEntity
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettingsWithTypeBuilder
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.util.Identifier

abstract class ConiumEntityBuilder(val identifier: Identifier) {
    val templates: MutableList<ConiumEntityTemplate> get() = templates()

    abstract fun templates(): MutableList<ConiumEntityTemplate>

    fun addTemplates(templates: List<ConiumEntityTemplate>): ConiumEntityBuilder {
        this.templates.addAll(templates)
        return this
    }

    fun build(): EntityType.Builder<ConiumEntity> {
        // TODO entity type.
        val entitySettings = ConiumEntitySettings()

        val type = EntityType.Builder.create({ type, world ->
            ConiumEntity(
                type,
                world,
                entitySettings.compute("awa:group_name").also {
                    // Here applies settings change.
                }
            ).also { it.applyTemplates(this.templates) }
        }, SpawnGroup.MISC)

        return ConiumEntity.createType(
            this,
            ConiumEntitySettingsWithTypeBuilder(type, entitySettings)
        )
    }
}