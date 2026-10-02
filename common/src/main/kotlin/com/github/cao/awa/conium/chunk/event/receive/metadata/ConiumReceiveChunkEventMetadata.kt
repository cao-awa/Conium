package com.github.cao.awa.conium.chunk.event.receive.metadata
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket
import net.minecraft.world.level.chunk.LevelChunk

class ConiumReceiveChunkEventMetadata(val context: ConiumEventContext<ClientboundLevelChunkWithLightPacket>) : ConiumEventMetadata<ClientboundLevelChunkWithLightPacket, ConiumReceiveChunkEventMetadata>() {
    val worldChunk: LevelChunk = this.context[ConiumEventArgTypes.WORLD_CHUNK]
}