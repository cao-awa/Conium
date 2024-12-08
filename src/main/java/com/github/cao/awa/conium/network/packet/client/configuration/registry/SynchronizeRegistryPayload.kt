package com.github.cao.awa.conium.network.packet.client.configuration.registry

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.network.packet.client.configuration.ConiumClientConfigurationPacket
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.github.cao.awa.conium.server.ConiumDedicatedServer
import com.github.cao.awa.conium.server.datapack.ConiumContentDatapack
import com.google.gson.JsonParser
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientConfigurationNetworkHandler
import net.minecraft.network.PacketByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.Identifier
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class SynchronizeRegistryPayload : ConiumClientConfigurationPacket(IDENTIFIER) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumSynchronizeRegistryPayload")

        @JvmField
        val IDENTIFIER = CustomPayload.Id<SynchronizeRegistryPayload>(Identifier.of("conium:synchronize_registry"))

        @JvmField
        val CODEC: PacketCodec<PacketByteBuf, SynchronizeRegistryPayload> = PacketCodec.ofStatic(
            Companion::encode,
            Companion::decode
        )

        fun encode(buf: PacketByteBuf, packet: SynchronizeRegistryPayload) {
            ConiumDedicatedServer.loadDatapacks.datapacks.let { datapacks: MutableMap<Identifier, ConiumContentDatapack> ->
                buf.writeVarInt(datapacks.size)
                for ((identifier: Identifier, datapack: ConiumContentDatapack) in datapacks) {
                    buf.writeIdentifier(identifier)
                    buf.writeVarInt(datapack.contents.size)
                    for ((resourceIdentifier: Identifier, content: String) in datapack.contents) {
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
        LOGGER.info("Registry synchronizing: ")
        for ((identifier, datapack) in Conium.pendingDatapack.datapacks) {
            LOGGER.info("-- $identifier")
            for ((resourceIdentifier, content) in datapack.contents) {
                LOGGER.info("$resourceIdentifier >  $content")
            }
        }

        Conium.coniumItemManager!!.resetRegistries()

        Conium.pendingDatapack.datapacks.let { datapacks ->
            datapacks[ConiumRegistryKeys.ITEM.value]?.let { datapack ->
                Conium.coniumItemManager!!.resetRegistries()
                for ((identifier: Identifier, content: String) in datapack.contents) {
                    Conium.coniumItemManager!!.load(identifier, JsonParser.parseString(content).asJsonObject)
                }
            }

            datapacks[ConiumRegistryKeys.BLOCK.value]?.let { datapack ->
                Conium.coniumBlockManager!!.resetRegistries()
                for ((identifier: Identifier, content: String) in datapack.contents) {
                    Conium.coniumBlockManager!!.load(identifier, JsonParser.parseString(content).asJsonObject)
                }
            }

            datapacks[ConiumRegistryKeys.ENTITY.value]?.let { datapack ->
                Conium.coniumEntityManager!!.resetRegistries()
                for ((identifier: Identifier, content: String) in datapack.contents) {
                    Conium.coniumEntityManager!!.load(identifier, JsonParser.parseString(content).asJsonObject)
                }
            }
        }
    }
}
