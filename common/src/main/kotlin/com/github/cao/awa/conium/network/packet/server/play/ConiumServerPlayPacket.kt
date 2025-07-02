package com.github.cao.awa.conium.network.packet.server.play

import com.github.cao.awa.conium.network.packet.sender.PacketSender
import com.github.cao.awa.conium.network.packet.server.ConiumServerPacket
import net.minecraft.network.listener.PacketListener
import net.minecraft.network.packet.CustomPayload.Id
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity

abstract class ConiumServerPlayPacket(identifier: Id<out ConiumServerPlayPacket>) : ConiumServerPacket(identifier) {
    override fun arising(side: MinecraftServer, player: ServerPlayerEntity?, sender: PacketSender, networkHandler: PacketListener) = arisingPlay(side, player!!, sender, networkHandler as ServerPlayNetworkHandler)

    abstract fun arisingPlay(client: MinecraftServer, player: ServerPlayerEntity, sender: PacketSender, networkHandler: ServerPlayNetworkHandler)
}