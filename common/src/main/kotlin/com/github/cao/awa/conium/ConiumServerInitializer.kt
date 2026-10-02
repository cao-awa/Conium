package com.github.cao.awa.conium

import com.github.cao.awa.conium.client.ConiumClient
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.network.packet.client.configuration.registry.SynchronizeRegistryPayload
import com.github.cao.awa.conium.network.registry.ConiumPacketRegistry
import com.github.cao.awa.conium.server.ConiumDedicatedServer
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.server.network.ServerConfigurationPacketListenerImpl
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ConiumServerInitializer {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumServerInitializer")
    }

    fun onInitializeServer() {
        ConiumClient.willNeverInitialized()
        ConiumDedicatedServer.onInitialized()

        // Initialize for network packets.
        ConiumPacketRegistry.registerAll()
        ConiumPacketRegistry.packets.let { packets: MutableMap<CustomPacketPayload.Type<*>, StreamCodec<FriendlyByteBuf, *>> ->
            LOGGER.info("Loaded ${packets.size} network packets")
            Conium.debug(
                "Loaded {} network packets: {}",
                { packets.size },
                { packets },
                LOGGER::info
            )
        }

        ConiumEvent.serverConfigurationConnection.subscribe { networkHandler: ServerConfigurationPacketListenerImpl, _ ->
            networkHandler.send(
                SynchronizeRegistryPayload().packet
            )
            true
        }
    }
}
