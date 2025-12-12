package com.github.cao.awa.conium.chunk.event.received.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.world.chunk.WorldChunk

class ConiumReceivedChunkEventMetadata(val context: ConiumEventContext<WorldChunk>) : ConiumEventMetadata<WorldChunk, ConiumReceivedChunkEventMetadata>() {
    val worldChunk: WorldChunk = this.context[ConiumEventArgTypes.WORLD_CHUNK]
}