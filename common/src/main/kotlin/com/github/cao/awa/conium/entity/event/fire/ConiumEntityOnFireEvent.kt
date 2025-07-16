package com.github.cao.awa.conium.entity.event.fire

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective2
import net.minecraft.entity.Entity

class ConiumEntityOnFireEvent : ConiumEvent<ParameterSelective2<Boolean, Entity, Int>, ConiumEntityOnFireEventMetadata>(
    ConiumEventType.ENTITY_ON_FIRE
) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requiresAny(
            ConiumEventArgTypes.ENTITY,
            ConiumEventArgTypes.INT
        ).arise { identity: Any, entity: Entity, fireTicks: Int ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(entity, fireTicks)
            }
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumEntityOnFireEventMetadata {
        return ConiumEntityOnFireEventMetadata(context)
    }
}
