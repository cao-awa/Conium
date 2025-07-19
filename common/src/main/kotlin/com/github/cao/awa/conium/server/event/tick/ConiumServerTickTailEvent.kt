package com.github.cao.awa.conium.server.event.tick

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective0
import net.minecraft.server.MinecraftServer

class ConiumServerTickTailEvent : ConiumEvent<MinecraftServer, ConiumServerTickTailEventMetadata, ParameterSelective0<Boolean>>(
    ConiumEventType.SERVER_TICK_TAIL
) {
    override fun requirement(): ConiumArisingEventContext<MinecraftServer, out ParameterSelective> {
        return requires(ConiumEventArgTypes.SERVER) { identity: Any ->
            noFailure(identity, ParameterSelective0<Boolean>::arise)
        }
    }

    override fun metadata(context: ConiumEventContext<MinecraftServer>): ConiumServerTickTailEventMetadata {
        return ConiumServerTickTailEventMetadata(context)
    }
}
