package com.github.cao.awa.conium.entity.event.sprint

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.entity.Entity

class ConiumEntitySprintingEvent : ConiumEvent<ParameterSelective1<Boolean, Entity>, ConiumEntitySprintingEventMetadata>(
    ConiumEventType.ENTITY_SPRINTING
) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.ENTITY
        ).arise { identity: Any, entity: Entity ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(entity)
            }
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumEntitySprintingEventMetadata {
        return ConiumEntitySprintingEventMetadata(context)
    }
}
