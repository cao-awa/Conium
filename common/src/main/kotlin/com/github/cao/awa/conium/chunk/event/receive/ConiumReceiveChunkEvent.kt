package com.github.cao.awa.conium.chunk.event.receive
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.chunk.event.receive.metadata.ConiumReceiveChunkEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket
import net.minecraft.world.level.chunk.LevelChunk

class ConiumReceiveChunkEvent : ConiumEvent<ClientboundLevelChunkWithLightPacket, ConiumReceiveChunkEventMetadata, ParameterSelective1<Boolean, LevelChunk>, ConiumInactiveEventType>(
    ConiumEventType.RECEIVE_CHUNK,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<ClientboundLevelChunkWithLightPacket, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.CHUNK_DATA_S2C_PACKET,
            ConiumEventArgTypes.WORLD_CHUNK
        ) { identity: ClientboundLevelChunkWithLightPacket, chunk: LevelChunk ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(chunk)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<ClientboundLevelChunkWithLightPacket>): ConiumReceiveChunkEventMetadata {
        return ConiumReceiveChunkEventMetadata(context)
    }
}
