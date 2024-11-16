package com.github.cao.awa.conium.entity.event.die

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective3
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.world.World

class ConiumEntityDeadEvent : ConiumEvent<ParameterSelective3<Boolean, World, LivingEntity, DamageSource>>() {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.LIVING_ENTITY,
            ConiumEventArgTypes.DAMAGE_SOURCE
        ).attach(
            forever(ConiumEventType.ENTITY_DEAD)
        ).arise { identity, world, livingEntity, damageSource ->
            noFailure(identity) {
                it.arise(world, livingEntity, damageSource)
            }
        }
    }
}
