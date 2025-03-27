package com.github.cao.awa.conium.chunk.event.receive

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective1
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.block.AbstractBlock.AbstractBlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.chunk.WorldChunk

class ConiumReceivedChunkEvent : ConiumEvent<ParameterSelective1<Boolean, WorldChunk>>(ConiumEventType.RECEIVED_CHUNK) {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.WORLD_CHUNK
        ).arise { identity: Any, chunk: WorldChunk ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(chunk)
            }
        }
    }
}
