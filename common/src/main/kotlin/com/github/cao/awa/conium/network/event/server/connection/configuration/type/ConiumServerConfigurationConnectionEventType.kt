package com.github.cao.awa.conium.network.event.server.connection.configuration.type

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import com.github.cao.awa.conium.item.event.use.metadata.ConiumItemUseEventMetadata
import com.github.cao.awa.conium.item.event.used.metadata.ConiumItemUsedEventMetadata
import com.github.cao.awa.conium.network.event.server.connection.configuration.metadata.ConiumServerConfigurationConnectionEventMetadata
import net.minecraft.item.Item
import net.minecraft.server.network.ServerConfigurationNetworkHandler

class ConiumServerConfigurationConnectionEventType: ConiumCancelableEventType<ServerConfigurationNetworkHandler, ConiumServerConfigurationConnectionEventMetadata, Unit, ConiumInactiveEventMetadata>(
    "server_configuration_connection",
    "ServerConfigurationNetworkHandler",
    ConiumEvent.Companion::serverConfigurationConnection
) {
}