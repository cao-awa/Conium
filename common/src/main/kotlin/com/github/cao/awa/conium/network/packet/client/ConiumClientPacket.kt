package com.github.cao.awa.conium.network.packet.client

import com.github.cao.awa.conium.network.ConiumPacket
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.network.packet.CustomPayload.Id
import net.minecraft.network.packet.s2c.common.CustomPayloadS2CPacket

abstract class ConiumClientPacket(identifier: Id<out ConiumClientPacket>) : ConiumPacket<MinecraftClient, ClientPlayerEntity, CustomPayloadS2CPacket>(identifier) {
    override fun createPacket(): CustomPayloadS2CPacket = CustomPayloadS2CPacket(this)
}