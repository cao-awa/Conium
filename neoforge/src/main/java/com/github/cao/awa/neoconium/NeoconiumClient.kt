package com.github.cao.awa.neoconium

import com.github.cao.awa.conium.ConiumClientInitializer
import com.github.cao.awa.conium.network.packet.client.configuration.ConiumClientConfigurationPacket
import com.github.cao.awa.conium.network.packet.client.play.ConiumClientPlayPacket
import com.github.cao.awa.conium.network.packet.sender.PacketSender
import com.github.cao.awa.conium.network.registry.ConiumPacketRegister
import net.minecraft.client.MinecraftClient
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent

@Mod("neoconium", dist = [Dist.CLIENT])
class NeoconiumClient {
    @SubscribeEvent
    fun register(event: RegisterPayloadHandlersEvent) {
        // Sets the current network version
        val registrar = event.registrar("1")
        ConiumPacketRegister.implementConfigurationToClient<ConiumClientConfigurationPacket> { id, codec ->
            registrar.configurationToClient(id, codec) { payload, context ->
                val sender = context.connection()
                payload.arising(MinecraftClient.getInstance(), null, PacketSender(sender::send), context.listener())
            }
        }
        ConiumPacketRegister.implementPlayToClient<ConiumClientPlayPacket> { id, codec ->
            registrar.playToClient(id, codec) { payload, context ->
                val sender = context.connection()
                payload.arising(MinecraftClient.getInstance(), null, PacketSender(sender::send), context.listener())
            }
        }

        ConiumClientInitializer().onInitializeClient()
    }
}
