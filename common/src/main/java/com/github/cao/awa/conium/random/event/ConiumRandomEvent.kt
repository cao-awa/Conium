package com.github.cao.awa.conium.random.event

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective0

class ConiumRandomEvent : ConiumEvent<ParameterSelective0<Boolean>, ConiumRandomEventMetadata>(ConiumEventType.RANDOM) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requires().arise { identity: Any ->
            noFailure(identity, ParameterSelective0<Boolean>::arise)
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumRandomEventMetadata {
        return ConiumRandomEventMetadata(context)
    }
}
