package com.github.cao.awa.conium.network.packet.client.configuration

import com.github.cao.awa.conium.network.packet.client.ConiumClientPacket
import com.github.cao.awa.conium.network.packet.sender.PacketSender
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientConfigurationPacketListenerImpl
import net.minecraft.client.player.LocalPlayer
import net.minecraft.network.PacketListener
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type

abstract class ConiumClientConfigurationPacket(identifier: Type<out ConiumClientConfigurationPacket>) : ConiumClientPacket(identifier) {
    override fun arising(side: Minecraft, player: LocalPlayer?, sender: PacketSender, networkHandler: PacketListener) = arising(side, sender, networkHandler as ClientConfigurationPacketListenerImpl)

    abstract fun arising(client: Minecraft, sender: PacketSender, networkHandler: ClientConfigurationPacketListenerImpl)
}