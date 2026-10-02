package com.github.cao.awa.conium.network.packet.sender

import net.minecraft.network.protocol.Packet

class PacketSender(val sender: (Packet<*>) -> Unit)
