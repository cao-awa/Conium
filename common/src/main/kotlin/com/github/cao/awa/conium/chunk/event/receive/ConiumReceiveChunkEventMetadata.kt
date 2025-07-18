package com.github.cao.awa.conium.chunk.event.receive

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.WORLD_CHUNK
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket
import net.minecraft.world.chunk.WorldChunk

class ConiumReceiveChunkEventMetadata(val context: ConiumEventContext<ChunkDataS2CPacket>) : ConiumEventMetadata<ChunkDataS2CPacket>() {
    val worldChunk: WorldChunk = this.context[WORLD_CHUNK]
}
