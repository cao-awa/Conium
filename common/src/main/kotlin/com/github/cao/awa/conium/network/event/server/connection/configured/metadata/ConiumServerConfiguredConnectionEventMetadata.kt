package com.github.cao.awa.conium.network.event.server.connection.configured.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerConfigurationPacketListenerImpl

class ConiumServerConfiguredConnectionEventMetadata(val context: ConiumEventContext<ServerConfigurationPacketListenerImpl>): ConiumEventMetadata<ServerConfigurationPacketListenerImpl, ConiumServerConfiguredConnectionEventMetadata>() {
    val serverConfigurationNetworkHandler: ServerConfigurationPacketListenerImpl = this.context[ConiumEventArgTypes.SERVER_CONFIGURATION_NETWORK_HANDLER]
    val server: MinecraftServer = this.context[ConiumEventArgTypes.SERVER]
}