package com.github.cao.awa.conium.network.packet.client.play

import com.github.cao.awa.conium.network.packet.client.ConiumClientPacket
import com.github.cao.awa.conium.network.packet.sender.PacketSender
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientPacketListener
import net.minecraft.client.player.LocalPlayer
import net.minecraft.network.PacketListener
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type

abstract class ConiumClientPlayPacket(identifier: Type<out ConiumClientPlayPacket>) : ConiumClientPacket(identifier) {
    override fun arising(side: Minecraft, player: LocalPlayer?, sender: PacketSender, networkHandler: PacketListener) = arisingPlay(side, player!!, sender, networkHandler as ClientPacketListener)

    abstract fun arisingPlay(client: Minecraft, player: LocalPlayer, sender: PacketSender, networkHandler: ClientPacketListener)
}