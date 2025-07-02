package com.github.cao.awa.conium.entity.event.fire.extinguish

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective2
import net.minecraft.entity.Entity

class ConiumEntityExtinguishFireEvent : ConiumEvent<ParameterSelective2<Boolean, Entity, Int>, ConiumEntityExtinguishFireEventMetadata>(
    ConiumEventType.ENTITY_EXTINGUISH_FIRE
) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.ENTITY,
            ConiumEventArgTypes.INT
        ).arise { identity: Any, entity: Entity, leftFireTicks: Int ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(entity, leftFireTicks)
            }
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumEntityExtinguishFireEventMetadata {
        return ConiumEntityExtinguishFireEventMetadata(context)
    }
}
