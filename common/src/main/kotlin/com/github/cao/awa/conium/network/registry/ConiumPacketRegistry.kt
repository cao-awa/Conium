package com.github.cao.awa.conium.network.registry

import com.github.cao.awa.conium.network.ConiumPacket
import com.github.cao.awa.conium.network.packet.client.configuration.ConiumClientConfigurationPacket
import com.github.cao.awa.conium.network.packet.client.configuration.registry.SynchronizeRegistryPayload
import com.github.cao.awa.conium.network.packet.client.play.ConiumClientPlayPacket
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Experimental
class ConiumPacketRegistry {
    companion object {
        val packets: MutableMap<Type<*>, StreamCodec<FriendlyByteBuf, *>> = HashMap()
        val registries: MutableMap<Type<*>, ConiumPacket<*, *, *>> = HashMap()

        @JvmStatic
        fun registerAll() {
            registerConfigurationToClient(SynchronizeRegistryPayload.IDENTIFIER, SynchronizeRegistryPayload.CODEC)
        }

        fun <P: ConiumClientConfigurationPacket> registerConfigurationToClient(id: Type<P>, codec: StreamCodec<FriendlyByteBuf, P>) {
            ConiumPacketRegister.registerConfigurationToClient(id, codec)
            packets[id] = codec
        }

        fun registerPlayToClient(id: Type<ConiumClientPlayPacket>, codec: StreamCodec<FriendlyByteBuf, ConiumClientPlayPacket>) {
            ConiumPacketRegister.registerPlayToClient(id, codec)
            packets[id] = codec
        }
    }
}
