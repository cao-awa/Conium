package com.github.cao.awa.conium.network.packet.server.play

import com.github.cao.awa.conium.network.packet.sender.PacketSender
import com.github.cao.awa.conium.network.packet.server.ConiumServerPacket
import net.minecraft.network.PacketListener
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerGamePacketListenerImpl
import net.minecraft.server.level.ServerPlayer

abstract class ConiumServerPlayPacket(identifier: Type<out ConiumServerPlayPacket>) : ConiumServerPacket(identifier) {
    override fun arising(side: MinecraftServer, player: ServerPlayer?, sender: PacketSender, networkHandler: PacketListener) = arising(side, player!!, sender, networkHandler as ServerGamePacketListenerImpl)

    abstract fun arising(client: MinecraftServer, player: ServerPlayer, sender: PacketSender, networkHandler: ServerGamePacketListenerImpl)
}