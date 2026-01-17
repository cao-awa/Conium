package com.github.cao.awa.conium.network.registry

import com.github.cao.awa.conium.kotlin.extent.manipulate.doCast
import com.github.cao.awa.conium.network.ConiumPacket
import com.github.cao.awa.conium.network.packet.client.configuration.ConiumClientConfigurationPacket
import com.github.cao.awa.conium.network.packet.client.play.ConiumClientPlayPacket
import net.minecraft.network.PacketByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload.Id

fun interface ConiumPacketRegister<P : ConiumPacket<*, *, *>> {
    companion object {
        private var toClientConfigurationRegister: ConiumPacketRegister<ConiumClientConfigurationPacket> = ConiumPacketRegister { _, _ ->
            throw IllegalStateException("No configuration network packet impl now!")
        }

        private var toClientPlayRegister: ConiumPacketRegister<ConiumClientPlayPacket> = ConiumPacketRegister { _, _ ->
            throw IllegalStateException("No play network packet impl now!")
        }

        fun <P : ConiumClientConfigurationPacket> implementConfigurationToClient(impl: ConiumPacketRegister<P>) {
            try {
                this.toClientConfigurationRegister = impl.doCast()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        fun <P : ConiumClientConfigurationPacket> unionConfigurationToClient(impl: ConiumPacketRegister<P>) {
            val oldImpl = this.toClientConfigurationRegister
            this.toClientConfigurationRegister = impl.doCast()
            val newImpl = this.toClientConfigurationRegister
            this.toClientConfigurationRegister = ConiumPacketRegister { id, codec ->
                oldImpl.register(id, codec)
                newImpl.register(id, codec)
            }
        }

        fun <P : ConiumClientConfigurationPacket> registerConfigurationToClient(id: Id<P>, codec: PacketCodec<PacketByteBuf, P>) {
            this.toClientConfigurationRegister.register(id.doCast(), codec.doCast())
        }

        fun <P : ConiumClientPlayPacket> implementPlayToClient(impl: ConiumPacketRegister<P>) {
            this.toClientPlayRegister = impl.doCast()
        }

        fun <P : ConiumClientPlayPacket> unionPlayToClient(impl: ConiumPacketRegister<P>) {
            val oldImpl = this.toClientPlayRegister
            this.toClientPlayRegister = impl.doCast()
            val newImpl = this.toClientPlayRegister
            this.toClientPlayRegister = ConiumPacketRegister { id, codec ->
                oldImpl.register(id, codec)
                newImpl.register(id, codec)
            }
        }

        fun <P : ConiumClientPlayPacket> registerPlayToClient(id: Id<P>, codec: PacketCodec<PacketByteBuf, P>) {
            this.toClientPlayRegister.register(id.doCast(), codec.doCast())
        }
    }

    fun register(id: Id<P>, packet: PacketCodec<PacketByteBuf, P>)
}
