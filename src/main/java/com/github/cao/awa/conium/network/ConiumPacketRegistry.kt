package com.github.cao.awa.conium.network

import com.github.cao.awa.conium.network.packet.client.ConiumClientPacket
import com.github.cao.awa.conium.network.packet.client.configuration.registry.SynchronizeRegistryPayload
import com.github.cao.awa.conium.network.packet.server.ConiumServerPacket
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.ServerConfigurationNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.fabricmc.fabric.impl.networking.PayloadTypeRegistryImpl
import net.minecraft.network.PacketByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload.Id
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Experimental
class ConiumPacketRegistry {
    companion object {
        val packets: MutableMap<Id<*>, PacketCodec<PacketByteBuf, *>> = CollectionFactor.hashMap()

        @JvmStatic
        fun registerClient() {
            registerClientConfigurationTypeAndReceiver(SynchronizeRegistryPayload.IDENTIFIER, SynchronizeRegistryPayload.CODEC)
        }

        @JvmStatic
        fun registerServer() {
            registerClientConfigurationType(SynchronizeRegistryPayload.IDENTIFIER, SynchronizeRegistryPayload.CODEC)
        }

        fun <P : ConiumClientPacket> registerClientConfigurationTypeAndReceiver(id: Id<P>, codec: PacketCodec<PacketByteBuf, P>) {
            registerClientConfigurationType(id, codec)
            registerClientConfigurationReceiver(id)
        }

        fun <P : ConiumClientPacket> registerClientPlayTypeAndReceiver(id: Id<P>, codec: PacketCodec<PacketByteBuf, P>) {
            registerClientPlayType(id, codec)
            registerClientPlayReceiver(id)
        }

        fun <P : ConiumClientPacket> registerClientConfigurationType(id: Id<P>, codec: PacketCodec<PacketByteBuf, P>) {
            PayloadTypeRegistryImpl.CONFIGURATION_S2C.register(id, codec)
            this.packets[id] = codec
        }

        fun <P : ConiumClientPacket> registerClientPlayType(id: Id<P>, codec: PacketCodec<PacketByteBuf, P>) {
            PayloadTypeRegistryImpl.PLAY_S2C.register(id, codec)
            this.packets[id] = codec
        }

        fun <P : ConiumClientPacket> registerClientConfigurationReceiver(id: Id<P>) {
            ClientConfigurationNetworking.registerGlobalReceiver(id) { packet, context ->
                try {
                    packet.arising(context.client(), null, context.responseSender(), context.networkHandler())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        fun <P : ConiumClientPacket> registerClientPlayReceiver(id: Id<P>) {
            ClientPlayNetworking.registerGlobalReceiver(id) { packet, context ->
                packet.arising(context.client(), context.player(), context.responseSender(), context.player().networkHandler)
            }
        }

        fun <P : ConiumServerPacket> registerServerConfigurationTypeAndReceiver(id: Id<P>, codec: PacketCodec<PacketByteBuf, P>) {
            registerServerConfigurationType(id, codec)
            registerServerConfigurationReceiver(id)
        }

        fun <P : ConiumServerPacket> registerServerPlayTypeAndReceiver(id: Id<P>, codec: PacketCodec<PacketByteBuf, P>) {
            registerServerPlayType(id, codec)
            registerServerPlayReceiver(id)
        }

        fun <P : ConiumServerPacket> registerServerConfigurationType(id: Id<P>, codec: PacketCodec<PacketByteBuf, P>) {
            PayloadTypeRegistryImpl.CONFIGURATION_C2S.register(id, codec)
            this.packets[id] = codec
        }

        fun <P : ConiumServerPacket> registerServerPlayType(id: Id<P>, codec: PacketCodec<PacketByteBuf, P>) {
            PayloadTypeRegistryImpl.PLAY_C2S.register(id, codec)
            this.packets[id] = codec
        }

        fun <P : ConiumServerPacket> registerServerConfigurationReceiver(id: Id<P>) {
            ServerConfigurationNetworking.registerGlobalReceiver(id) { packet, context ->
                packet.arising(context.server(), null, context.responseSender(), context.networkHandler())
            }
        }

        fun <P : ConiumServerPacket> registerServerPlayReceiver(id: Id<P>) {
            ServerPlayNetworking.registerGlobalReceiver(id) { packet, context ->
                packet.arising(context.server(), context.player(), context.responseSender(), context.player().networkHandler)
            }
        }
    }
}
