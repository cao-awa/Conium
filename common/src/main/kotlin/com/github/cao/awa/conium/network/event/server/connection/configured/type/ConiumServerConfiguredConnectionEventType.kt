package com.github.cao.awa.conium.network.event.server.connection.configured.type

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.network.event.server.connection.configuration.metadata.ConiumServerConfigurationConnectionEventMetadata
import com.github.cao.awa.conium.network.event.server.connection.configured.metadata.ConiumServerConfiguredConnectionEventMetadata
import net.minecraft.server.network.ServerConfigurationNetworkHandler

class ConiumServerConfiguredConnectionEventType: ConiumNoCancelableEventType<ServerConfigurationNetworkHandler, ConiumServerConfiguredConnectionEventMetadata, ServerConfigurationNetworkHandler, ConiumServerConfiguredConnectionEventMetadata>(
    "server_configured_connection",
    "ServerConfigurationNetworkHandler",
    ConiumEvent.Companion::serverConfiguredConnection
)