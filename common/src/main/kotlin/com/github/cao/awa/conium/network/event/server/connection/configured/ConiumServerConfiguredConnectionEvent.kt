package com.github.cao.awa.conium.network.event.server.connection.configured

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.network.event.server.connection.configured.metadata.ConiumServerConfiguredConnectionEventMetadata
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective2
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerConfigurationNetworkHandler

class ConiumServerConfiguredConnectionEvent : ConiumEvent<ServerConfigurationNetworkHandler, ConiumServerConfiguredConnectionEventMetadata, ParameterSelective2<Boolean, ServerConfigurationNetworkHandler, MinecraftServer>, ConiumInactiveEventType>(
    ConiumEventType.SERVER_CONFIGURED_CONNECTION,
    { ConiumEventType.INACTIVE }
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

    override fun metadata(context: ConiumEventContext<ServerConfigurationNetworkHandler>): ConiumServerConfiguredConnectionEventMetadata {
        return ConiumServerConfiguredConnectionEventMetadata(context)
    }
}