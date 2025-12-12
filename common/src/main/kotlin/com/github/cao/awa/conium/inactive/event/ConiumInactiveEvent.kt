package com.github.cao.awa.conium.inactive.event

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective0

class ConiumInactiveEvent: ConiumEvent<Unit, ConiumEmptyEventMetadata, ParameterSelective0<Boolean>, ConiumInactiveEventType>(
    ConiumEventType.INACTIVE,
    { ConiumEventType.INACTIVE }
) {
    override fun metadata(context: ConiumEventContext<Unit>): ConiumEmptyEventMetadata = ConiumEmptyEventMetadata()

    override fun requirement(): ConiumArisingEventContext<Unit, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.UNIT
        ) { identity: Unit ->
            true
        }
    }
}