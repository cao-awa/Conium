package com.github.cao.awa.conium.server.event.tick.start

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective0
import com.github.cao.awa.conium.server.event.tick.tail.metadata.ConiumServerTickTailEventMetadata
import com.github.cao.awa.conium.server.event.tick.start.metadata.ConiumServerTickEventMetadata
import com.github.cao.awa.conium.server.event.tick.tail.type.ConiumServerTickTailEventType
import net.minecraft.server.MinecraftServer

class ConiumServerTickEvent : ConiumEvent<MinecraftServer, ConiumServerTickEventMetadata, ParameterSelective0<Boolean>, ConiumServerTickTailEventType>(
    ConiumEventType.SERVER_TICK,
    { ConiumEventType.SERVER_TICK_TAIL }
) {
    override fun requirement(): ConiumArisingEventContext<MinecraftServer, out ParameterSelective> {
        return requires(ConiumEventArgTypes.SERVER) { server: MinecraftServer ->
            noFailure(server, ParameterSelective0<Boolean>::arise)
        }
    }

    override fun metadata(context: ConiumEventContext<MinecraftServer>): ConiumServerTickEventMetadata {
        return ConiumServerTickEventMetadata(context)
    }
}
