package com.github.cao.awa.conium.block.event.fluid.schedule.ticked

import com.github.cao.awa.conium.block.event.fluid.schedule.ticked.metadata.ConiumFluidScheduleTickedEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.block.AbstractBlock
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.world.tick.ScheduledTickView

class ConiumFluidScheduleTickedEvent : ConiumEvent<Fluid, ConiumFluidScheduleTickedEventMetadata, ParameterSelective5<Boolean, ServerWorld, BlockPos, AbstractBlock.AbstractBlockState, FluidState, ScheduledTickView>, ConiumInactiveEventType>(
    ConiumEventType.FLUID_SCHEDULE_TICKED,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<Fluid, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.FLUID,
            ConiumEventArgTypes.SERVER_WORLD,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.FLUID_STATE,
            ConiumEventArgTypes.SCHEDULED_TICK_VIEW
        ) { identity: Any, world: ServerWorld, pos: BlockPos, blockState: AbstractBlock.AbstractBlockState, fluidState: FluidState, scheduler: ScheduledTickView ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, pos, blockState, fluidState, scheduler)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Fluid>): ConiumFluidScheduleTickedEventMetadata {
        return ConiumFluidScheduleTickedEventMetadata(context)
    }
}