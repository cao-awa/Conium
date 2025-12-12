package com.github.cao.awa.conium.chunk.event.receive.type

import com.github.cao.awa.conium.chunk.event.receive.metadata.ConiumReceiveChunkEventMetadata
import com.github.cao.awa.conium.chunk.event.received.metadata.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket
import net.minecraft.world.chunk.WorldChunk

class ConiumReceiveChunkEventType: ConiumCancelableEventType<ChunkDataS2CPacket, ConiumReceiveChunkEventMetadata, WorldChunk, ConiumReceivedChunkEventMetadata>(
    "receive_chunk",
    "WorldChunk",
    ConiumEvent.Companion::receiveChunk
)