package com.github.cao.awa.conium.entity.event.die.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.world.World

class ConiumEntityDieEventMetadata(val context: ConiumEventContext<EntityType<*>>) : ConiumEventMetadata<EntityType<*>, ConiumEntityDieEventMetadata>() {
    val world: World = this.context[ConiumEventArgTypes.WORLD]
    val livingEntity: LivingEntity = this.context[ConiumEventArgTypes.LIVING_ENTITY]
    val damageSource: DamageSource = this.context[ConiumEventArgTypes.DAMAGE_SOURCE]
}