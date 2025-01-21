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

class ConiumicServer : DedicatedServerModInitializer {
    override fun onInitializeServer() {
        // Register the configuring packet to synchronize registry.
        ServerConfigurationConnectionEvents.CONFIGURE.register { handler: ServerConfigurationNetworkHandler, server: MinecraftServer ->
            val context = ConiumEvent.enterConfigurationConnection.request()

            context[ConiumEventArgTypes.SERVER] = server

            if (context.presaging(handler)) {
                context.arising(handler)
            }
        }

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
