package com.github.cao.awa.conium.block.event.tick

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.block.AbstractBlock.AbstractBlockState
import net.minecraft.block.Block
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.tick.ScheduledTickView

class ConiumBlockScheduleTickedEvent : ConiumEvent<ParameterSelective5<Boolean, ServerWorld, BlockPos, AbstractBlockState, ScheduledTickView, Random>, ConiumBlockScheduleTickedEventMetadata>(
    ConiumEventType.BLOCK_SCHEDULE_TICKED
) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.BLOCK,
            ConiumEventArgTypes.SERVER_WORLD,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.SCHEDULED_TICK_VIEW,
            ConiumEventArgTypes.RANDOM
        ).arise { identity: Block, world: ServerWorld, pos: BlockPos, blockState: AbstractBlockState, scheduler: ScheduledTickView, random: Random ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, pos, blockState, scheduler, random)
            }
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumBlockScheduleTickedEventMetadata {
        return ConiumBlockScheduleTickedEventMetadata(context)
    }
}
