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
import net.minecraft.world.chunk.WorldChunk

class ConiumReceivedChunkEvent : ConiumEvent<WorldChunk, ConiumReceivedChunkEventMetadata, ParameterSelective1<Boolean, WorldChunk>>(ConiumEventType.RECEIVED_CHUNK) {
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
