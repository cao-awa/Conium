package com.github.cao.awa.conium.network.event.server.connection.configuration.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.script.index.common.SERVER
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerConfigurationNetworkHandler

class ConiumServerConfigurationConnectionEventMetadata(val context: ConiumEventContext<ServerConfigurationNetworkHandler>): ConiumEventMetadata<ServerConfigurationNetworkHandler, ConiumServerConfigurationConnectionEventMetadata>() {
    val serverConfigurationNetworkHandler: ServerConfigurationNetworkHandler = this.context[ConiumEventArgTypes.SERVER_CONFIGURATION_NETWORK_HANDLER]
    val server: MinecraftServer = this.context[SERVER]
}