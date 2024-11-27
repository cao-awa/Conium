package com.github.cao.awa.conium.block.event.tick.fluid

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.block.AbstractBlock.AbstractBlockState
import net.minecraft.fluid.FluidState
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.world.tick.ScheduledTickView

class ConiumFluidScheduleTickedEvent : ConiumEvent<ParameterSelective5<Boolean, ServerWorld, BlockPos, AbstractBlockState, FluidState, ScheduledTickView>>() {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.SERVER_WORLD,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.FLUID_STATE,
            ConiumEventArgTypes.SCHEDULE_TICK_VIEW
        ).attach(
            forever(ConiumEventType.FLUID_SCHEDULE_TICKED)
        ).arise { identity: Any, world: ServerWorld, pos: BlockPos, blockState: AbstractBlockState, fluidState: FluidState, scheduler: ScheduledTickView ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, pos, blockState, fluidState, scheduler)
            }
        }
    }
}
