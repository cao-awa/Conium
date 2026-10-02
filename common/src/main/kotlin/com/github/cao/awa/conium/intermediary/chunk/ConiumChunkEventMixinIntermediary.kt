package com.github.cao.awa.conium.intermediary.chunk
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.intermediary.ConiumEventMixinIntermediary.fireEventCancelable
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket
import net.minecraft.server.MinecraftServer
import net.minecraft.world.level.chunk.ChunkAccess
import net.minecraft.world.level.chunk.LevelChunk

/**
 * Conium server event intermediary triggers.
 *
 * @see Chunk
 * @see ConiumEventType.RECEIVE_CHUNK
 * @see ConiumEventType.RECEIVED_CHUNK
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
object ConiumChunkEventMixinIntermediary {
    /**
     * Trigger the chunk receiving event on receiving chunk at client.
     *
     * @see MinecraftServer
     * @see ConiumEventType.RECEIVE_CHUNK
     * @see ConiumEventType.RECEIVED_CHUNK
     *
     * @param server the minecraft server
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun fireReceiveChunkEvent(packet: ClientboundLevelChunkWithLightPacket): Boolean {
        return fireEventCancelable(
            ConiumEventType.RECEIVE_CHUNK,
            packet
        ) { context: ConiumArisingEventContext<*, *> ->
            context[ConiumEventArgTypes.CHUNK_DATA] = packet.chunkData
            context[ConiumEventArgTypes.LIGHT_DATA] = packet.lightData
        }
    }

    /**
     * Trigger the chunk received event on receiving chunk at client.
     *
     * @see MinecraftServer
     * @see ConiumEventType.RECEIVE_CHUNK
     * @see ConiumEventType.RECEIVED_CHUNK
     *
     * @param server the minecraft server
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun fireReceivedChunkEvent(chunk: LevelChunk): Boolean {
        return fireEventCancelable(
            ConiumEventType.RECEIVED_CHUNK,
            chunk
        ) { context: ConiumArisingEventContext<*, *> ->
            context[ConiumEventArgTypes.WORLD_CHUNK] = chunk
        }
    }
}
