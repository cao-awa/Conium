package com.github.cao.awa.conium.chunk.event.received

import com.github.cao.awa.conium.chunk.event.received.metadata.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.chunk.event.received.type.ConiumReceivedChunkEventType
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.world.chunk.WorldChunk

class ConiumReceivedChunkEvent : ConiumEvent<WorldChunk, ConiumReceivedChunkEventMetadata, ParameterSelective1<Boolean, WorldChunk>, ConiumInactiveEventType>(
    ConiumEventType.RECEIVED_CHUNK,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<WorldChunk, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.WORLD_CHUNK,
            ConiumEventArgTypes.WORLD_CHUNK
        ).arise { identity: WorldChunk, worldChunk: WorldChunk ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(worldChunk)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<WorldChunk>): ConiumReceivedChunkEventMetadata {
        return ConiumReceivedChunkEventMetadata(context)
    }
}
