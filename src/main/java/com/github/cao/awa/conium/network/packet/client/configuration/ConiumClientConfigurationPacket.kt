package com.github.cao.awa.conium.network.packet.client.configuration

import com.github.cao.awa.conium.network.packet.client.ConiumClientPacket
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientConfigurationNetworkHandler
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.network.listener.PacketListener
import net.minecraft.network.packet.CustomPayload.Id

abstract class ConiumClientConfigurationPacket(identifier: Id<out ConiumClientConfigurationPacket>) : ConiumClientPacket(identifier) {
    override fun arising(side: MinecraftClient, player: ClientPlayerEntity?, sender: PacketSender, networkHandler: PacketListener) = arising(side, sender, networkHandler as ClientConfigurationNetworkHandler)

    abstract fun arising(client: MinecraftClient, sender: PacketSender, networkHandler: ClientConfigurationNetworkHandler)
}