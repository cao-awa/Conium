package com.github.cao.awa.conium.block.event.schedule.ticked
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.block.event.schedule.ticked.metadata.ConiumBlockScheduleTickedEventMetadata
import com.github.cao.awa.conium.block.event.schedule.ticked.type.ConiumBlockScheduleTickedEventType
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.Block
import net.minecraft.server.level.ServerLevel
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
// import ScheduledTickView

class ConiumBlockScheduleTickedEvent : ConiumEvent<Block, ConiumBlockScheduleTickedEventMetadata, ParameterSelective5<Boolean, ServerLevel, BlockPos, BlockBehaviour.BlockStateBase, ScheduledTickView, Random>, ConiumInactiveEventType>(
    ConiumEventType.BLOCK_SCHEDULE_TICKED,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<Block, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.BLOCK,
            ConiumEventArgTypes.SERVER_WORLD,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.SCHEDULED_TICK_VIEW,
            ConiumEventArgTypes.RANDOM
        ) { identity: Block, world: ServerLevel, pos: BlockPos, blockState: BlockBehaviour.BlockStateBase, scheduler: ScheduledTickView, random: Random ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, pos, blockState, scheduler, random)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Block>): ConiumBlockScheduleTickedEventMetadata {
        return ConiumBlockScheduleTickedEventMetadata(context)
    }
}