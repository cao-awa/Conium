package com.github.cao.awa.conium.entity.event.extinguished

import com.github.cao.awa.conium.entity.event.extinguished.metadata.ConiumEntityExtinguishedFireEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType

class ConiumEntityExtinguishedFireEvent : ConiumEvent<EntityType<*>, ConiumEntityExtinguishedFireEventMetadata, ParameterSelective1<Boolean, Entity>, ConiumInactiveEventType>(
    ConiumEventType.ENTITY_EXTINGUISHED_FIRE,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<EntityType<*>, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.ENTITY_TYPE,
            ConiumEventArgTypes.ENTITY
        ).arise { identity: Any, entity: Entity ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(entity)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<EntityType<*>>): ConiumEntityExtinguishedFireEventMetadata {
        return ConiumEntityExtinguishedFireEventMetadata(context)
    }
}