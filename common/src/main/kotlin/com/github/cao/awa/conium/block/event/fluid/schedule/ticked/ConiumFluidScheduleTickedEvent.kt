package com.github.cao.awa.conium.block.event.fluid.schedule.ticked
import com.github.cao.awa.conium.mapping.yarn.*

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
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import net.minecraft.server.level.ServerLevel
import net.minecraft.core.BlockPos
// import ScheduledTickView

class ConiumFluidScheduleTickedEvent : ConiumEvent<Fluid, ConiumFluidScheduleTickedEventMetadata, ParameterSelective5<Boolean, ServerLevel, BlockPos, BlockBehaviour.BlockStateBase, FluidState, ScheduledTickView>, ConiumInactiveEventType>(
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
        ) { identity: Any, world: ServerLevel, pos: BlockPos, blockState: BlockBehaviour.BlockStateBase, fluidState: FluidState, scheduler: ScheduledTickView ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, pos, blockState, fluidState, scheduler)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Fluid>): ConiumFluidScheduleTickedEventMetadata {
        return ConiumFluidScheduleTickedEventMetadata(context)
    }
}