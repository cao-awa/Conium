package com.github.cao.awa.conium.network.event

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective2
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerConfigurationNetworkHandler

class ConiumServerConfigurationConnectionEvent : ConiumEvent<ParameterSelective2<Boolean, ServerConfigurationNetworkHandler, MinecraftServer>, ConiumServerConfigurationConnectionEventMetadata>(
    ConiumEventType.SERVER_CONFIGURATION_CONNECTION
) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return ConiumEventContextBuilder.requiresAny(
            ConiumEventArgTypes.SERVER_CONFIGURATION_NETWORK_HANDLER,
            ConiumEventArgTypes.SERVER
        ).arise { identity: Any, networkHandler: ServerConfigurationNetworkHandler, server: MinecraftServer ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(networkHandler, server)
            }
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumServerConfigurationConnectionEventMetadata {
        return ConiumServerConfigurationConnectionEventMetadata(context)
    }
}
