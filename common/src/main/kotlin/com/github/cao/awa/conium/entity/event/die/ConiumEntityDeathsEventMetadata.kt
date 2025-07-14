package com.github.cao.awa.conium.entity.event.die

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.DAMAGE_SOURCE
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.LIVING_ENTITY
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.WORLD
import com.github.cao.awa.conium.mapping.yarn.DamageSource
import net.minecraft.entity.LivingEntity
import net.minecraft.world.World

abstract class ConiumEntityDeathsEventMetadata(val context: ConiumEventContext) : ConiumEventMetadata() {
    val world: World = this.context[WORLD]
    val livingEntity: LivingEntity = this.context[LIVING_ENTITY]
    val damageSource: DamageSource = this.context[DAMAGE_SOURCE]
}
