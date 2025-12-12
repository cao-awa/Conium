package com.github.cao.awa.conium.entity.event.dead.metadata

import com.github.cao.awa.conium.entity.event.die.metadata.ConiumEntityDieEventMetadata
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.mapping.yarn.DamageSource
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.world.World

class ConiumEntityDeadEventMetadata(val context: ConiumEventContext<EntityType<*>>) : ConiumEventMetadata<EntityType<*>, ConiumEntityDeadEventMetadata>() {
    val world: World = this.context[ConiumEventArgTypes.WORLD]
    val livingEntity: LivingEntity = this.context[ConiumEventArgTypes.LIVING_ENTITY]
    val damageSource: DamageSource = this.context[ConiumEventArgTypes.DAMAGE_SOURCE]
}