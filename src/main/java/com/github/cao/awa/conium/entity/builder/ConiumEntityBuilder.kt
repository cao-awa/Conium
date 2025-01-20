package com.github.cao.awa.conium.entity.builder

import com.github.cao.awa.conium.entity.ConiumEntity
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettings
import com.github.cao.awa.conium.entity.setting.ConiumEntitySettingsWithTypeBuilder
import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import com.github.cao.awa.conium.template.ConiumBuilderWithTemplates
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.util.Identifier
import net.minecraft.world.World

abstract class ConiumEntityBuilder(val identifier: Identifier) : ConiumBuilderWithTemplates<
        ConiumEntityBuilder,
        Unit,
        EntityType.Builder<ConiumEntity>,
        ConiumEntityTemplate>(
    ::build
) {
    companion object {
        fun build(builder: ConiumEntityBuilder): EntityType.Builder<ConiumEntity> {
            val type: EntityType.Builder<ConiumEntity> = EntityType.Builder.create({ type: EntityType<ConiumEntity>, world: World ->
                ConiumEntity(
                    type,
                    world,
                    builder.entitySettings
                ).also { it.applyTemplates(builder.templates()) }
            }, SpawnGroup.MISC)

            builder.groupTemplates.forEach { (name: String, templates: MutableList<ConiumEntityTemplate>) ->
                builder.entitySettings.migrate(
                    name,
                    ConiumEntitySettings.create(templates, type)
                )
            }

            return ConiumEntity.createType(
                builder,
                ConiumEntitySettingsWithTypeBuilder(type, builder.entitySettings)
            )
        }
    }

    val groupTemplates: MutableMap<String, MutableList<ConiumEntityTemplate>> = CollectionFactor.hashMap()
    val entitySettings: ConiumEntitySettings = ConiumEntitySettings()

    fun addTemplates(group: String, templates: MutableList<ConiumEntityTemplate>): ConiumEntityBuilder {
        this.groupTemplates.computeIfAbsent(group) { CollectionFactor.arrayList() }.addAll(templates)
        return this
    }
}