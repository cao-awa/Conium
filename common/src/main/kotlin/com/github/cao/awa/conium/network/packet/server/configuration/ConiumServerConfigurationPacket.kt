package com.github.cao.awa.conium.network.packet.server.configuration

import com.github.cao.awa.conium.network.packet.sender.PacketSender
import com.github.cao.awa.conium.network.packet.server.ConiumServerPacket
import net.minecraft.client.multiplayer.ClientConfigurationPacketListenerImpl
import net.minecraft.network.PacketListener
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer

abstract class ConiumServerConfigurationPacket(identifier: Type<out ConiumServerConfigurationPacket>) : ConiumServerPacket(identifier) {
    override fun arising(side: MinecraftServer, player: ServerPlayer?, sender: PacketSender, networkHandler: PacketListener) = arising(side, sender, networkHandler as ClientConfigurationPacketListenerImpl)

    abstract fun arising(server: MinecraftServer, sender: PacketSender, networkHandler: ClientConfigurationPacketListenerImpl)
}