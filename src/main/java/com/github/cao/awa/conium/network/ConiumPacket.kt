package com.github.cao.awa.conium.network

import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.network.listener.PacketListener
import net.minecraft.network.packet.CustomPayload
import net.minecraft.network.packet.CustomPayload.Id

abstract class ConiumPacket<S, P : PlayerEntity, C>(val identifier: Id<out ConiumPacket<S, P, C>>) : CustomPayload {
    val packet: C get() = createPacket()

    override fun getId(): Id<out CustomPayload> = this.identifier

    abstract fun arising(side: S, player: P?, sender: PacketSender, networkHandler: PacketListener)

    abstract fun createPacket(): C
}
