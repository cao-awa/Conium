package com.github.cao.awa.conium.chunk.event.receive

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.world.chunk.WorldChunk

class ConiumReceivedChunkEvent : ConiumEvent<ParameterSelective1<Boolean, WorldChunk>, ConiumReceivedChunkEventMetadata>(ConiumEventType.RECEIVED_CHUNK) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requiresAny(
            ConiumEventArgTypes.WORLD_CHUNK
        ).arise { identity: Any, chunk: WorldChunk ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(chunk)
            }
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumReceivedChunkEventMetadata {
        return ConiumReceivedChunkEventMetadata(context)
    }
}
