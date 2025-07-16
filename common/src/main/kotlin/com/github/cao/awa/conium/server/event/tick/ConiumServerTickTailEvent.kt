package com.github.cao.awa.conium.server.event.tick

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresWith
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective0

class ConiumServerTickTailEvent : ConiumEvent<ParameterSelective0<Boolean>, ConiumServerTickTailEventMetadata>(
    ConiumEventType.SERVER_TICK_TAIL
) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requiresWith(ConiumEventArgTypes.SERVER).arise { identity: Any ->
            noFailure(identity, ParameterSelective0<Boolean>::arise)
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumServerTickTailEventMetadata {
        return ConiumServerTickTailEventMetadata(context)
    }
}
