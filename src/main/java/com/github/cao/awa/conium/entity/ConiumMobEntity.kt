package com.github.cao.awa.conium.entity

import com.github.cao.awa.conium.entity.template.ConiumEntityTemplate
import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.MobEntity
import net.minecraft.world.World

class ConiumMobEntity(entityType: EntityType<ConiumMobEntity>, world: World): MobEntity(entityType, world) {
    fun applyTemplates(templates: List<ConiumEntityTemplate>) {
        templates.forEach {
            it.attach(this)
        }

        templates.forEach {
            it.complete(this)
        }
    }
}
