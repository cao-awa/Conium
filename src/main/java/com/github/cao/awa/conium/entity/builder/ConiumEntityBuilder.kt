package com.github.cao.awa.conium.entity.builder

import com.github.cao.awa.conium.entity.ConiumMobEntity
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

    fun build(): EntityType.Builder<ConiumMobEntity> {
        // TODO entity type.
        val type = EntityType.Builder.create({ type, world ->
            ConiumMobEntity(type, world).also { it.applyTemplates(this.templates) }
        }, SpawnGroup.MISC)

        return type
    }
}