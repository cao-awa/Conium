package com.github.cao.awa.conium.network

import com.github.cao.awa.conium.network.packet.sender.PacketSender
import net.minecraft.client.Minecraft
import net.minecraft.client.player.LocalPlayer
import net.minecraft.world.entity.player.Player
import net.minecraft.network.PacketListener
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.resources.Identifier

/**
 * The network packet convertible data.
 *
 * @param identifier the identifier of packet
 * @param S the game side
 * @param P the player that receiving this packet
 * @param C the type of real packet ([ConiumPacket] is data)
 *
 * @see Packet
 * @see CustomPacketPayload
 * @see ClientboundCustomPayloadPacket
 * @see ServerboundCustomPayloadPacket
 * @see PacketListener
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
abstract class ConiumPacket<S, P : Player, C>(val identifier: Type<out ConiumPacket<S, P, C>>) : CustomPacketPayload {
    val packet: C get() = createPacket()

    /**
     *
     * @return the identifier of this packet
     *
     * @see Type
     * @see Identifier
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun type(): Type<out ConiumPacket<S, P, C>> = this.identifier

    /**
     *
     * @param side the game side
     * @param player the receiving player
     * @param sender the packet sender
     * @param networkHandler the network handler
     *
     * @see Minecraft
     * @see MinecraftServer
     * @see LocalPlayer
     * @see ServerPlayer
     * @see PacketSender
     * @see PacketListener
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    abstract fun arising(side: S, player: P?, sender: PacketSender, networkHandler: PacketListener)

    /**
     * Create the packet to send, the result should be ClientboundCustomPayloadPacket or ServerboundCustomPayloadPacket, otherwise is unexpected.
     *
     * @see CustomPacketPayload
     * @see ClientboundCustomPayloadPacket
     * @see ServerboundCustomPayloadPacket
     *
     * @return the custom payload packet
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    abstract fun createPacket(): C
}
