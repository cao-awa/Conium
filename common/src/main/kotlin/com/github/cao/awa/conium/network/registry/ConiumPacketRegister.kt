package com.github.cao.awa.conium.network.registry

import com.github.cao.awa.conium.extent.manipulate.cast
import com.github.cao.awa.conium.network.ConiumPacket
import com.github.cao.awa.conium.network.packet.client.configuration.ConiumClientConfigurationPacket
import com.github.cao.awa.conium.network.packet.client.play.ConiumClientPlayPacket
import com.github.cao.awa.translator.structuring.cast.Caster
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
                this.toClientConfigurationRegister = impl.cast()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        fun <P : ConiumClientConfigurationPacket> unionConfigurationToClient(impl: ConiumPacketRegister<P>) {
            val oldImpl = this.toClientConfigurationRegister
            this.toClientConfigurationRegister = impl.cast()
            val newImpl = this.toClientConfigurationRegister
            this.toClientConfigurationRegister = ConiumPacketRegister { id, codec ->
                oldImpl.register(id, codec)
                newImpl.register(id, codec)
            }
        }

        fun <P : ConiumClientConfigurationPacket> registerConfigurationToClient(id: Id<P>, codec: PacketCodec<PacketByteBuf, P>) {
            this.toClientConfigurationRegister.register(id.cast(), Caster.cast(codec))
        }

        fun <P : ConiumClientPlayPacket> implementPlayToClient(impl: ConiumPacketRegister<P>) {
            this.toClientPlayRegister = impl.cast()
        }

        fun <P : ConiumClientPlayPacket> unionPlayToClient(impl: ConiumPacketRegister<P>) {
            val oldImpl = this.toClientPlayRegister
            this.toClientPlayRegister = impl.cast()
            val newImpl = this.toClientPlayRegister
            this.toClientPlayRegister = ConiumPacketRegister { id, codec ->
                oldImpl.register(id, codec)
                newImpl.register(id, codec)
            }
        }

        fun <P : ConiumClientPlayPacket> registerPlayToClient(id: Id<P>, codec: PacketCodec<PacketByteBuf, P>) {
            this.toClientPlayRegister.register(id.cast(), Caster.cast(codec))
        }
    }

    fun register(id: Id<P>, packet: PacketCodec<PacketByteBuf, P>)
}
