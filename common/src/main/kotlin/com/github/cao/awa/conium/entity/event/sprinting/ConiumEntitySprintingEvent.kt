package com.github.cao.awa.conium.entity.event.sprinting

import com.github.cao.awa.conium.entity.event.sprinting.metadata.ConiumEntitySprintingEventMetadata
import com.github.cao.awa.conium.entity.event.sprinting.stop.type.ConiumEntityStopSprintEventType
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType

class ConiumEntitySprintingEvent : ConiumEvent<EntityType<*>, ConiumEntitySprintingEventMetadata, ParameterSelective1<Boolean, Entity>, ConiumEntityStopSprintEventType>(
    ConiumEventType.Companion.ENTITY_SPRINTING,
    { ConiumEventType.Companion.ENTITY_STOP_SPRINT }
) {
    override fun requirement(): ConiumArisingEventContext<EntityType<*>, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.ENTITY_TYPE,
            ConiumEventArgTypes.ENTITY
        ) { identity: Any, entity: Entity ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(entity)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<EntityType<*>>): ConiumEntitySprintingEventMetadata {
        return ConiumEntitySprintingEventMetadata(context)
    }
}