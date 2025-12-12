package com.github.cao.awa.conium.chunk.event.received.type

import com.github.cao.awa.conium.chunk.event.received.metadata.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import net.minecraft.world.chunk.WorldChunk

class ConiumReceivedChunkEventType: ConiumCancelableEventType<WorldChunk, ConiumReceivedChunkEventMetadata, WorldChunk, ConiumReceivedChunkEventMetadata>(
    "received_chunk",
    "WorldChunk",
    ConiumEvent.Companion::receivedChunk
)