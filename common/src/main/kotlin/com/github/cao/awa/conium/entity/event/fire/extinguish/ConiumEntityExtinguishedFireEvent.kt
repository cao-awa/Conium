package com.github.cao.awa.conium.entity.event.fire.extinguish

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.entity.Entity

class ConiumEntityExtinguishedFireEvent : ConiumEvent<ParameterSelective1<Boolean, Entity>, ConiumEntityExtinguishedFireEventMetadata>(
    ConiumEventType.ENTITY_EXTINGUISHED_FIRE
) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requiresAny(
            ConiumEventArgTypes.ENTITY
        ).arise { identity: Any, entity: Entity ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(entity)
            }
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumEntityExtinguishedFireEventMetadata {
        return ConiumEntityExtinguishedFireEventMetadata(context)
    }
}
