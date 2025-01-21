package com.github.cao.awa.conium.network.packet.client.play

import com.github.cao.awa.conium.network.packet.client.ConiumClientPacket
import com.github.cao.awa.conium.network.packet.sender.PacketSender
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayNetworkHandler
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.network.listener.PacketListener
import net.minecraft.network.packet.CustomPayload.Id

abstract class ConiumClientPlayPacket(identifier: Id<out ConiumClientPlayPacket>) : ConiumClientPacket(identifier) {
    override fun arising(side: MinecraftClient, player: ClientPlayerEntity?, sender: PacketSender, networkHandler: PacketListener) = arisingPlay(side, player!!, sender, networkHandler as ClientPlayNetworkHandler)

    abstract fun arisingPlay(client: MinecraftClient, player: ClientPlayerEntity, sender: PacketSender, networkHandler: ClientPlayNetworkHandler)
}