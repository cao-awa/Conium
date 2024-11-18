package com.github.cao.awa.conium.entity.event.tick

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective1
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.world.World

class ConiumEntityTickEvent : ConiumEvent<ParameterSelective1<Boolean, Entity>>() {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.ENTITY
        ).attach(
            forever(ConiumEventType.ENTITY_TICK)
        ).arise { identity, entity ->
            noFailure(identity) {
                it.arise(entity)
            }
        }
    }
}
