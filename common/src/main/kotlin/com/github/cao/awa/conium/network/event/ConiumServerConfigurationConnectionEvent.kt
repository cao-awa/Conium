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

class ConiumServerConfigurationConnectionEvent : ConiumEvent<ServerConfigurationNetworkHandler, ConiumServerConfigurationConnectionEventMetadata, ParameterSelective2<Boolean, ServerConfigurationNetworkHandler, MinecraftServer>>(
    ConiumEventType.SERVER_CONFIGURATION_CONNECTION
) {
    override fun requirement(): ConiumArisingEventContext<ServerConfigurationNetworkHandler, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.SERVER_CONFIGURATION_NETWORK_HANDLER,
            ConiumEventArgTypes.SERVER
        ).arise { networkHandler: ServerConfigurationNetworkHandler, server: MinecraftServer ->
            noFailure(networkHandler) { parameterSelective ->
                parameterSelective(networkHandler, server)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<ServerConfigurationNetworkHandler>): ConiumServerConfigurationConnectionEventMetadata {
        return ConiumServerConfigurationConnectionEventMetadata(context)
    }
}
