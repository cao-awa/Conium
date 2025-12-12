package com.github.cao.awa.conium.entity.event.fire

import com.github.cao.awa.conium.entity.event.fire.metadata.ConiumEntityOnFireEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective2
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType

class ConiumEntityOnFireEvent : ConiumEvent<EntityType<*>, ConiumEntityOnFireEventMetadata, ParameterSelective2<Boolean, Entity, Int>, ConiumInactiveEventType>(
    ConiumEventType.ENTITY_ON_FIRE,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<EntityType<*>, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.ENTITY_TYPE,
            ConiumEventArgTypes.ENTITY,
            ConiumEventArgTypes.INT
        ).arise { identity: Any, entity: Entity, fireTicks: Int ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(entity, fireTicks)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<EntityType<*>>): ConiumEntityOnFireEventMetadata {
        return ConiumEntityOnFireEventMetadata(context)
    }
}
