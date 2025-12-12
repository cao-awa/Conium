package com.github.cao.awa.conium.entity.event.tick

import com.github.cao.awa.conium.entity.event.tick.metadata.ConiumEntityTickEventMetadata
import com.github.cao.awa.conium.entity.event.ticked.type.ConiumEntityTickedEventType
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType

class ConiumEntityTickEvent : ConiumEvent<EntityType<*>, ConiumEntityTickEventMetadata, ParameterSelective1<Boolean, Entity>, ConiumEntityTickedEventType>(
    ConiumEventType.ENTITY_TICK,
    { ConiumEventType.ENTITY_TICKED }
) {
    override fun requirement(): ConiumArisingEventContext<EntityType<*>, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.ENTITY_TYPE,
            ConiumEventArgTypes.ENTITY
        ) { identity: Any, entity: Entity ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(entity)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<EntityType<*>>): ConiumEntityTickEventMetadata {
        return ConiumEntityTickEventMetadata(context)
    }
}
