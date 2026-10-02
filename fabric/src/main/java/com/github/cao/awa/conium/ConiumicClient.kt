package com.github.cao.awa.conium

import com.github.cao.awa.conium.network.packet.client.configuration.ConiumClientConfigurationPacket
import com.github.cao.awa.conium.network.packet.client.play.ConiumClientPlayPacket
import com.github.cao.awa.conium.network.packet.sender.PacketSender
import com.github.cao.awa.conium.network.registry.ConiumPacketRegister
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import org.apache.logging.log4j.LogManager

class ConiumicClient : ClientModInitializer {
    companion object {
        private val LOGGER = LogManager.getLogger("ConiumicClient")
    }

    // TODO remove fabric APIs.
    override fun onInitializeClient() {
        ConiumPacketRegister.implementConfigurationToClient<ConiumClientConfigurationPacket> { id, codec ->
            PayloadTypeRegistry.clientboundConfiguration().register(id, codec)

            ClientConfigurationNetworking.registerGlobalReceiver(id) { packet: ConiumClientConfigurationPacket, context: ClientConfigurationNetworking.Context ->
                val sender = context.responseSender()
                packet.arising(context.client(), null, PacketSender(sender::sendPacket), context.packetListener())
            }
        }

        ConiumPacketRegister.implementPlayToClient<ConiumClientPlayPacket> { id, codec ->
            PayloadTypeRegistry.clientboundPlay().register(id, codec)

            ClientPlayNetworking.registerGlobalReceiver(id) { packet: ConiumClientPlayPacket, context: ClientPlayNetworking.Context ->
                val sender = context.responseSender()
                packet.arising(context.client(), context.player(), PacketSender(sender::sendPacket), context.player().connection)
            }
        }

        // Initialize conium.
        ConiumClientInitializer().onInitializeClient()
    }
}
