@file:Suppress("unchecked_cast")

package com.github.cao.awa.conium.network.packet.client.configuration.registry

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.network.packet.client.configuration.ConiumClientConfigurationPacket
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.github.cao.awa.conium.server.ConiumDedicatedServer
import com.google.gson.JsonParser
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientConfigurationNetworkHandler
import net.minecraft.network.PacketByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.Identifier

class SynchronizeRegistryPayload : ConiumClientConfigurationPacket(IDENTIFIER) {
    companion object {
        @JvmField
        val IDENTIFIER = CustomPayload.Id<SynchronizeRegistryPayload>(Identifier.of("conium:synchronize_registry"))

        @JvmField
        val CODEC: PacketCodec<PacketByteBuf, SynchronizeRegistryPayload> = PacketCodec.ofStatic(
            Companion::encode,
            Companion::decode
        )

        fun encode(buf: PacketByteBuf, packet: SynchronizeRegistryPayload) {
            ConiumDedicatedServer.loadDatapacks.datapacks.let { datapacks ->
                buf.writeVarInt(datapacks.size)
                for ((identifier, datapack) in datapacks) {
                    buf.writeIdentifier(identifier)
                    buf.writeVarInt(datapack.contents.size)
                    for ((resourceIdentifier, content) in datapack.contents) {
                        buf.writeIdentifier(resourceIdentifier)
                        buf.writeString(content)
                    }
                }
            }
        }

        fun decode(buf: PacketByteBuf): SynchronizeRegistryPayload {
            var datapacks: Int = buf.readVarInt()
            while (datapacks > 0) {
                val identifier: Identifier = buf.readIdentifier()
                var resources: Int = buf.readVarInt()
                while (resources > 0) {
                    val resourceIdentifier: Identifier = buf.readIdentifier()
                    val content: String = buf.readString()
                    Conium.onLoadData(identifier, resourceIdentifier, content)
                    resources--
                }
                datapacks--
            }
            return SynchronizeRegistryPayload()
        }
    }

    override fun arising(client: MinecraftClient, sender: PacketSender, networkHandler: ClientConfigurationNetworkHandler) {
        println("Registry synchronized: ")
        for ((identifier, datapack) in Conium.pendingDatapack.datapacks) {
            println("-- $identifier")
            for ((resourceIdentifier, content) in datapack.contents) {
                println("$resourceIdentifier >  $content")
            }
        }

        Conium.coniumItemManager!!.resetRegistries()

        Conium.pendingDatapack.datapacks.let { datapacks ->
            datapacks[ConiumRegistryKeys.ITEM.value]?.let { datapack ->
                for ((identifier: Identifier, content: String) in datapack.contents) {
                    Conium.coniumItemManager!!.resetRegistries()
                    Conium.coniumItemManager!!.load(identifier, JsonParser.parseString(content).asJsonObject)
                }
            }

            datapacks[ConiumRegistryKeys.BLOCK.value]?.let { datapack ->
                for ((identifier: Identifier, content: String) in datapack.contents) {
                    Conium.coniumBlockManager!!.resetRegistries()
                    Conium.coniumBlockManager!!.load(identifier, JsonParser.parseString(content).asJsonObject)
                }
            }

            datapacks[ConiumRegistryKeys.ENTITY.value]?.let { datapack ->
                for ((identifier: Identifier, content: String) in datapack.contents) {
                    Conium.coniumEntityManager!!.resetRegistries()
                    Conium.coniumEntityManager!!.load(identifier, JsonParser.parseString(content).asJsonObject)
                }
            }
        }
    }
}
