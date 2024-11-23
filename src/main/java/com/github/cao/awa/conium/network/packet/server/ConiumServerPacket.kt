package com.github.cao.awa.conium.network.packet.server

import com.github.cao.awa.conium.network.ConiumPacket
import net.minecraft.network.packet.CustomPayload.Id
import net.minecraft.network.packet.c2s.common.CustomPayloadC2SPacket
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayerEntity

abstract class ConiumServerPacket(identifier: Id<out ConiumServerPacket>) : ConiumPacket<MinecraftServer, ServerPlayerEntity, CustomPayloadC2SPacket>(identifier) {
    override fun createPacket(): CustomPayloadC2SPacket = CustomPayloadC2SPacket(this)
}