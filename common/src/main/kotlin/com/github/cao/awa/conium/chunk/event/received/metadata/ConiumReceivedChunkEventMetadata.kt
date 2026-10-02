package com.github.cao.awa.conium.chunk.event.received.metadata
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.world.level.chunk.LevelChunk

class ConiumReceivedChunkEventMetadata(val context: ConiumEventContext<LevelChunk>) : ConiumEventMetadata<LevelChunk, ConiumReceivedChunkEventMetadata>() {
    val worldChunk: LevelChunk = this.context[ConiumEventArgTypes.WORLD_CHUNK]
}