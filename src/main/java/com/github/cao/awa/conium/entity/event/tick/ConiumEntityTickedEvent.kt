package com.github.cao.awa.conium.entity.event.tick

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.entity.Entity

class ConiumEntityTickedEvent : ConiumEvent<ParameterSelective1<Boolean, Entity>>() {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.ENTITY
        ).attach(
            forever(ConiumEventType.ENTITY_TICKED)
        ).arise { identity, entity ->
            noFailure(identity) {
                it.arise(entity)
            }
        }
    }
}
