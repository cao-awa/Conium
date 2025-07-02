package com.github.cao.awa.conium.network

import com.github.cao.awa.conium.network.packet.sender.PacketSender
import com.mojang.authlib.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.network.listener.PacketListener
import net.minecraft.network.packet.CustomPayload
import net.minecraft.network.packet.CustomPayload.Id
import net.minecraft.network.packet.Packet
import net.minecraft.network.packet.c2s.common.CustomPayloadC2SPacket
import net.minecraft.network.packet.s2c.common.CustomPayloadS2CPacket
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier

/**
 * The network packet convertible data.
 *
 * @param identifier the identifier of packet
 * @param S the game side
 * @param P the player that receiving this packet
 * @param C the type of real packet ([ConiumPacket] is data)
 *
 * @see Packet
 * @see CustomPayload
 * @see CustomPayloadS2CPacket
 * @see CustomPayloadC2SPacket
 * @see PacketListener
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
abstract class ConiumPacket<S, P : PlayerEntity, C>(val identifier: Id<out ConiumPacket<S, P, C>>) : CustomPayload {
    val packet: C get() = createPacket()

    /**
     *
     * @return the identifier of this packet
     *
     * @see Id
     * @see Identifier
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun getId(): Id<out ConiumPacket<S, P, C>> = this.identifier

    /**
     *
     * @param side the game side
     * @param player the receiving player
     * @param sender the packet sender
     * @param networkHandler the network handler
     *
     * @see MinecraftClient
     * @see MinecraftServer
     * @see ClientPlayerEntity
     * @see ServerPlayerEntity
     * @see PacketSender
     * @see PacketListener
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    abstract fun arising(side: S, player: P?, sender: PacketSender, networkHandler: PacketListener)

    /**
     * Create the packet to send, the result should be CustomPayloadS2CPacket or CustomPayloadC2SPacket, otherwise is unexpected.
     *
     * @see CustomPayload
     * @see CustomPayloadS2CPacket
     * @see CustomPayloadC2SPacket
     *
     * @return the custom payload packet
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    abstract fun createPacket(): C
}
