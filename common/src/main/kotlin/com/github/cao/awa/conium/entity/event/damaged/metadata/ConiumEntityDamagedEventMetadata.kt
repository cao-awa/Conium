package com.github.cao.awa.conium.entity.event.damaged.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.DAMAGE_AMOUNT
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.DAMAGE_SOURCE
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.LIVING_ENTITY
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.WORLD
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.world.World

class ConiumEntityDamagedEventMetadata(val context: ConiumEventContext<EntityType<*>>) : ConiumEventMetadata<EntityType<*>, ConiumEntityDamagedEventMetadata>() {
    val world: World = this.context[WORLD]
    val livingEntity: LivingEntity = this.context[LIVING_ENTITY]
    val damageSource: DamageSource = this.context[DAMAGE_SOURCE]
    val damageAmount: Float = this.context[DAMAGE_AMOUNT]
}
