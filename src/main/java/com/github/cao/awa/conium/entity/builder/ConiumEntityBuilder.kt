package com.github.cao.awa.conium.entity.builder

import com.github.cao.awa.conium.entity.ConiumEntity
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettingsWithTypeBuilder
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.util.Identifier

abstract class ConiumEntityBuilder(val identifier: Identifier) {
    val templates: MutableList<ConiumEntityTemplate> = CollectionFactor.arrayList()
    val groupTemplates: MutableMap<String, MutableList<ConiumEntityTemplate>> = CollectionFactor.hashMap()
    val entitySettings = ConiumEntitySettings()

    fun addTemplates(group: String, templates: MutableList<ConiumEntityTemplate>): ConiumEntityBuilder {
        this.groupTemplates.computeIfAbsent(group) { CollectionFactor.arrayList() }.addAll(templates)
        return this
    }

    fun addTemplates(templates: MutableList<ConiumEntityTemplate>): ConiumEntityBuilder {
        this.templates.addAll(templates)
        return this
    }

    fun build(): EntityType.Builder<ConiumEntity> {
        val type = EntityType.Builder.create({ type, world ->
            ConiumEntity(
                type,
                world,
                this.entitySettings
            ).also { it.applyTemplates(this.templates) }
        }, SpawnGroup.MISC)

        this.groupTemplates.forEach { (name, templates) ->
            this.entitySettings.migrate(
                name,
                ConiumEntitySettings.create(templates, type)
            )
        }

        return ConiumEntity.createType(
            this,
            ConiumEntitySettingsWithTypeBuilder(type, this.entitySettings)
        )
    }
}