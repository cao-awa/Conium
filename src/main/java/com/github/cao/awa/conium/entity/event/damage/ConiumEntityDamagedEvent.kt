package com.github.cao.awa.conium.entity.event.damage

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.world.World

class ConiumEntityDamagedEvent : ConiumEvent<ParameterSelective4<Boolean, World, LivingEntity, DamageSource, Float>>() {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.LIVING_ENTITY,
            ConiumEventArgTypes.DAMAGE_SOURCE,
            ConiumEventArgTypes.FLOAT
        ).attach(
            forever(ConiumEventType.ENTITY_DAMAGED)
        ).arise { identity, world, livingEntity, damageSource, amount ->
            noFailure(identity) {
                it.arise(world, livingEntity, damageSource, amount)
            }
        }
    }
}
