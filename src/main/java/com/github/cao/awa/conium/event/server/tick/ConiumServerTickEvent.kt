package com.github.cao.awa.conium.event.server.tick

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.server.MinecraftServer

class ConiumServerTickEvent : ConiumEvent<ParameterSelective1<Boolean, MinecraftServer>>() {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.SERVER,
        ).attach(
            forever(ConiumEventType.SERVER_TICK)
        ).arise { identity, server ->
            noFailure(identity) {
                it.arise(server)
            }
        }
    }
}
