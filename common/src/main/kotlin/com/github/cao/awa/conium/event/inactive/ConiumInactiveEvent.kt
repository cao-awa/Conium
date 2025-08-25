package com.github.cao.awa.conium.event.inactive

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective0
import com.github.cao.awa.conium.script.index.common.ConiumEventType

class ConiumInactiveEvent: ConiumEvent<Unit, ConiumEmptyEventMetadata, ParameterSelective0<Boolean>>(
    ConiumEventType.INACTIVE
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