package com.github.cao.awa.conium

import com.github.cao.awa.conium.Conium.Companion.debug
import com.github.cao.awa.conium.client.ConiumClient
import com.github.cao.awa.conium.kotlin.extent.innate.asIt
import com.github.cao.awa.conium.network.ConiumPacketRegistry
import com.github.cao.awa.conium.network.packet.client.configuration.registry.SynchronizeRegistryPayload
import com.github.cao.awa.conium.server.ConiumDedicatedServer
import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.fabric.api.networking.v1.ServerConfigurationConnectionEvents
import net.minecraft.network.PacketByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload.Id
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerConfigurationNetworkHandler
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ConiumServerInitializer : DedicatedServerModInitializer {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumCServerInitializer")
    }

    override fun onInitializeServer() {
        ConiumClient.willNeverInitialized()
        ConiumDedicatedServer.onInitialized()

//        Conium.reloadCallbacks.add {
//            ConiumEventContextBuilder.request(
//                ConiumEventType.USED_BLOCK,
//                ConiumEventArgTypes.BLOCK_POS,
//                ConiumEventArgTypes.SERVER_PLAYER
//            ) { block, pos, player ->
//                player.networkHandler.sendPacket(
//                    SynchronizeRegistryPayload("Player ${player.name.literalString} used block '$block' at '$pos'").packet
//                )
//                true
//            }
//        }

        ServerConfigurationConnectionEvents.CONFIGURE.register { handler: ServerConfigurationNetworkHandler, server: MinecraftServer ->
            handler.sendPacket(
                SynchronizeRegistryPayload().packet
            )
        }

        ConiumPacketRegistry.registerServer()
        ConiumPacketRegistry.packets.let { packets: MutableMap<Id<*>, PacketCodec<PacketByteBuf, *>> ->
            LOGGER.info("Loaded ${packets.size} network packets")
            debug(
                "Loaded {} server network packets: {}",
                packets::size,
                packets::asIt,
                LOGGER::info
            )
        }
    }
}
