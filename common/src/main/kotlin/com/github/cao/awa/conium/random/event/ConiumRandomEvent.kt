package com.github.cao.awa.conium.random.event

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective0
import com.github.cao.awa.conium.random.event.metadata.ConiumRandomEventMetadata
import net.minecraft.util.Unit

class ConiumRandomEvent : ConiumEvent<Unit, ConiumRandomEventMetadata, ParameterSelective0<Boolean>, ConiumInactiveEventType>(
    ConiumEventType.RANDOM,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<Unit, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.MINECRAFT_UNIT
        ) { identity: Unit ->
            noFailure(identity, ParameterSelective0<Boolean>::arise)
        }
    }

    override fun metadata(context: ConiumEventContext<Unit>): ConiumRandomEventMetadata {
        return ConiumRandomEventMetadata(context)
    }
}
