package com.github.cao.awa.conium.network.packet.server.configuration

import com.github.cao.awa.conium.network.packet.sender.PacketSender
import com.github.cao.awa.conium.network.packet.server.ConiumServerPacket
import net.minecraft.client.network.ClientConfigurationNetworkHandler
import net.minecraft.network.listener.PacketListener
import net.minecraft.network.packet.CustomPayload.Id
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayerEntity

abstract class ConiumServerConfigurationPacket(identifier: Id<out ConiumServerConfigurationPacket>) : ConiumServerPacket(identifier) {
    override fun arising(side: MinecraftServer, player: ServerPlayerEntity?, sender: PacketSender, networkHandler: PacketListener) = arising(side, sender, networkHandler as ClientConfigurationNetworkHandler)

    abstract fun arising(server: MinecraftServer, sender: PacketSender, networkHandler: ClientConfigurationNetworkHandler)
}