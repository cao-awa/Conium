package com.github.cao.awa.conium

import com.github.cao.awa.conium.client.ConiumClient
import com.github.cao.awa.conium.dsl.DSLEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.network.packet.client.configuration.registry.SynchronizeRegistryPayload
import com.github.cao.awa.conium.network.registry.ConiumPacketRegistry
import com.github.cao.awa.conium.script.index.common.ConiumEventContextBuilder
import com.github.cao.awa.conium.server.ConiumDedicatedServer
import net.minecraft.network.PacketByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload
import net.minecraft.server.network.ServerConfigurationNetworkHandler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ConiumServerInitializer {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumCServerInitializer")
    }

    fun onInitializeServer() {
        ConiumClient.willNeverInitialized()
        ConiumDedicatedServer.onInitialized()

        // Initialize for network packets.
        ConiumPacketRegistry.registerAll()
        ConiumPacketRegistry.packets.let { packets: MutableMap<CustomPayload.Id<*>, PacketCodec<PacketByteBuf, *>> ->
            LOGGER.info("Loaded ${packets.size} network packets")
            Conium.debug(
                "Loaded {} client network packets: {}",
                { packets.size },
                { packets },
                LOGGER::info
            )
        }

        ConiumEvent.enterConfigurationConnection.subscribe { networkHandler: ServerConfigurationNetworkHandler, _ ->
            networkHandler.sendPacket(
                SynchronizeRegistryPayload().packet
            )
            true
        }
    }
}
