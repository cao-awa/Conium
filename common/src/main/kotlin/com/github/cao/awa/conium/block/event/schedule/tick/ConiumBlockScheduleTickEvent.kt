package com.github.cao.awa.conium.block.event.schedule.tick
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.block.event.schedule.tick.metadata.ConiumBlockScheduleTickEventMetadata
import com.github.cao.awa.conium.block.event.schedule.ticked.type.ConiumBlockScheduleTickedEventType
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase
import net.minecraft.world.level.block.Block
import net.minecraft.server.level.ServerLevel
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
// import ScheduledTickView

class ConiumBlockScheduleTickEvent : ConiumEvent<Block, ConiumBlockScheduleTickEventMetadata, ParameterSelective5<Boolean, ServerLevel, BlockPos, AbstractBlockState, ScheduledTickView, Random>, ConiumBlockScheduleTickedEventType>(
    ConiumEventType.BLOCK_SCHEDULE_TICK,
    { ConiumEventType.BLOCK_SCHEDULE_TICKED }
) {
    override fun metadata(context: ConiumEventContext<Block>): ConiumBlockScheduleTickEventMetadata = ConiumBlockScheduleTickEventMetadata(context)

    override fun requirement(): ConiumArisingEventContext<Block, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.BLOCK,
            ConiumEventArgTypes.SERVER_WORLD,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.SCHEDULED_TICK_VIEW,
            ConiumEventArgTypes.RANDOM
        ) { identity: Block, world: ServerLevel, pos: BlockPos, blockState: AbstractBlockState, scheduler: ScheduledTickView, random: Random ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, pos, blockState, scheduler, random)
            }
        }
    }
}
