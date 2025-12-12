package com.github.cao.awa.conium.server.event.tick.tail

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective0
import com.github.cao.awa.conium.server.event.tick.tail.metadata.ConiumServerTickTailEventMetadata
import net.minecraft.server.MinecraftServer

class ConiumServerTickTailEvent : ConiumEvent<MinecraftServer, ConiumServerTickTailEventMetadata, ParameterSelective0<Boolean>, ConiumInactiveEventType>(
    ConiumEventType.SERVER_TICK_TAIL,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<MinecraftServer, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(ConiumEventArgTypes.SERVER) { identity: Any ->
            noFailure(identity, ParameterSelective0<Boolean>::arise)
        }
    }

    override fun metadata(context: ConiumEventContext<MinecraftServer>): ConiumServerTickTailEventMetadata {
        return ConiumServerTickTailEventMetadata(context)
    }
}