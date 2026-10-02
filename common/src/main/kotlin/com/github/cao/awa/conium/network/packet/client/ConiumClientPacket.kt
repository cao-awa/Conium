package com.github.cao.awa.conium.network.packet.client

import com.github.cao.awa.conium.network.ConiumPacket
import net.minecraft.client.Minecraft
import net.minecraft.client.player.LocalPlayer
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket

abstract class ConiumClientPacket(identifier: Type<out ConiumClientPacket>) : ConiumPacket<Minecraft, LocalPlayer, ClientboundCustomPayloadPacket>(identifier) {
    override fun createPacket(): ClientboundCustomPayloadPacket = ClientboundCustomPayloadPacket(this)
}