package com.github.cao.awa.conium.intermediary.mixin.chunk

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.intermediary.mixin.ConiumEventMixinIntermediary.Companion.fireEvent
import com.github.cao.awa.conium.intermediary.mixin.ConiumEventMixinIntermediary.Companion.fireEventCancelable
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket
import net.minecraft.server.MinecraftServer
import net.minecraft.util.Unit
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.chunk.WorldChunk

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
class ConiumChunkEventMixinIntermediary {
    companion object {
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
        fun fireReceiveChunkEvent(packet: ChunkDataS2CPacket): Boolean {
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
        fun fireReceivedChunkEvent(chunk: WorldChunk): Boolean {
            return fireEventCancelable(
                ConiumEventType.RECEIVED_CHUNK,
                chunk
            ) { context: ConiumArisingEventContext<*, *> ->
                context[ConiumEventArgTypes.WORLD_CHUNK] = chunk
            }
        }
    }
}
