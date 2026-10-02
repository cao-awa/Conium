package com.github.cao.awa.conium.chunk.event.receive.type
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.chunk.event.receive.metadata.ConiumReceiveChunkEventMetadata
import com.github.cao.awa.conium.chunk.event.received.metadata.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket
import net.minecraft.world.level.chunk.LevelChunk

class ConiumReceiveChunkEventType: ConiumCancelableEventType<ClientboundLevelChunkWithLightPacket, ConiumReceiveChunkEventMetadata, LevelChunk, ConiumReceivedChunkEventMetadata>(
    "receive_chunk",
    "LevelChunk",
    ConiumEvent.Companion::receiveChunk
)