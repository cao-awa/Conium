package com.github.cao.awa.conium.network.event

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.SERVER_CONFIGURATION_NETWORK_HANDLER
import com.github.cao.awa.conium.script.index.common.SERVER
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerConfigurationNetworkHandler

class ConiumServerConfigurationConnectionEventMetadata(val context: ConiumEventContext<ServerConfigurationNetworkHandler>): ConiumEventMetadata<ServerConfigurationNetworkHandler>() {
    val serverConfigurationNetworkHandler: ServerConfigurationNetworkHandler = this.context[SERVER_CONFIGURATION_NETWORK_HANDLER]
    val server: MinecraftServer = this.context[SERVER]
}