package com.github.cao.awa.conium.chunk.event.receive

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket
import net.minecraft.world.chunk.WorldChunk

class ConiumReceiveChunkEvent : ConiumEvent<ChunkDataS2CPacket, ConiumReceiveChunkEventMetadata, ParameterSelective1<Boolean, WorldChunk>>(ConiumEventType.RECEIVE_CHUNK) {
    override fun requirement(): ConiumArisingEventContext<ChunkDataS2CPacket, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.CHUNK_DATA_S2C_PACKET,
            ConiumEventArgTypes.WORLD_CHUNK
        ) { identity: ChunkDataS2CPacket, chunk: WorldChunk ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(chunk)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<ChunkDataS2CPacket>): ConiumReceiveChunkEventMetadata {
        return ConiumReceiveChunkEventMetadata(context)
    }
}
