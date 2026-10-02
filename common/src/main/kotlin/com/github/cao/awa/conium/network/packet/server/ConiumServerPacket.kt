package com.github.cao.awa.conium.network.packet.server

import com.github.cao.awa.conium.network.ConiumPacket
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer

abstract class ConiumServerPacket(identifier: Type<out ConiumServerPacket>) : ConiumPacket<MinecraftServer, ServerPlayer, ServerboundCustomPayloadPacket>(identifier) {
    override fun createPacket(): ServerboundCustomPayloadPacket = ServerboundCustomPayloadPacket(this)
}