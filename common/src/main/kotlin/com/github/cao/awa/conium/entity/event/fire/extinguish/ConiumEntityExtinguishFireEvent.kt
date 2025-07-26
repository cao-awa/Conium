package com.github.cao.awa.conium.entity.event.fire.extinguish

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective2
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType

class ConiumEntityExtinguishFireEvent : ConiumEvent<EntityType<*>, ConiumEntityExtinguishFireEventMetadata, ParameterSelective2<Boolean, Entity, Int>>(
    ConiumEventType.ENTITY_EXTINGUISH_FIRE,
    { ConiumEventType.ENTITY_EXTINGUISHED_FIRE }
) {
    override fun requirement(): ConiumArisingEventContext<EntityType<*>, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.ENTITY_TYPE,
            ConiumEventArgTypes.ENTITY,
            ConiumEventArgTypes.INT
        ).arise { identity: Any, entity: Entity, leftFireTicks: Int ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(entity, leftFireTicks)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<EntityType<*>>): ConiumEntityExtinguishFireEventMetadata {
        return ConiumEntityExtinguishFireEventMetadata(context)
    }
}
