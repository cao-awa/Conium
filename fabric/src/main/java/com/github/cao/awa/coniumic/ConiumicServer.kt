package com.github.cao.awa.coniumic

import com.github.cao.awa.conium.ConiumServerInitializer
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.network.packet.client.configuration.ConiumClientConfigurationPacket
import com.github.cao.awa.conium.network.packet.client.play.ConiumClientPlayPacket
import com.github.cao.awa.conium.network.registry.ConiumPacketRegister
import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.fabric.api.networking.v1.ServerConfigurationConnectionEvents
import net.fabricmc.fabric.impl.networking.PayloadTypeRegistryImpl
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerConfigurationNetworkHandler
import org.apache.logging.log4j.LogManager

class ConiumicServer : DedicatedServerModInitializer {
    companion object {
        private val LOGGER = LogManager.getLogger("ConiumicServer")
    }

    override fun onInitializeServer() {
        // Network packets.
        ConiumPacketRegister.implementConfigurationToClient<ConiumClientConfigurationPacket> { id, codec ->
            PayloadTypeRegistryImpl.CONFIGURATION_S2C.register(id, codec)
        }

        ConiumPacketRegister.implementPlayToClient<ConiumClientPlayPacket> { id, codec ->
            PayloadTypeRegistryImpl.PLAY_C2S.register(id, codec)
        }

        // Initialize conium.
        ConiumServerInitializer().onInitializeServer()
    }
}
