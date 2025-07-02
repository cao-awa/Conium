package com.github.cao.awa.conium.network.registry

import com.github.cao.awa.conium.network.ConiumPacket
import com.github.cao.awa.conium.network.packet.client.configuration.ConiumClientConfigurationPacket
import com.github.cao.awa.conium.network.packet.client.configuration.registry.SynchronizeRegistryPayload
import com.github.cao.awa.conium.network.packet.client.play.ConiumClientPlayPacket
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.network.PacketByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload.Id
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Experimental
class ConiumPacketRegistry {
    companion object {
        val packets: MutableMap<Id<*>, PacketCodec<PacketByteBuf, *>> = CollectionFactor.hashMap()
        val registries: MutableMap<Id<*>, ConiumPacket<*, *, *>> = CollectionFactor.hashMap()

        @JvmStatic
        fun registerAll() {
            registerConfigurationToClient(SynchronizeRegistryPayload.IDENTIFIER, SynchronizeRegistryPayload.CODEC)
        }

        fun <P: ConiumClientConfigurationPacket>registerConfigurationToClient(id: Id<P>, codec: PacketCodec<PacketByteBuf, P>) {
            ConiumPacketRegister.registerConfigurationToClient(id, codec)
            packets[id] = codec
        }

        fun registerPlayToClient(id: Id<ConiumClientPlayPacket>, codec: PacketCodec<PacketByteBuf, ConiumClientPlayPacket>) {
            ConiumPacketRegister.registerPlayToClient(id, codec)
            packets[id] = codec
        }
    }
}
