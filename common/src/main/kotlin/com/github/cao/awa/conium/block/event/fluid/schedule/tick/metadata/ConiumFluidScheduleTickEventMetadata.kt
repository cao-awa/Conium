package com.github.cao.awa.conium.block.event.fluid.schedule.tick.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.mapping.yarn.ScheduledTickView
import com.github.cao.awa.conium.mapping.yarn.ServerWorld
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.util.math.BlockPos

class ConiumFluidScheduleTickEventMetadata(val context: ConiumEventContext<Fluid>) : ConiumEventMetadata<Fluid, ConiumFluidScheduleTickEventMetadata>() {
    val serverWorld: ServerWorld = this.context[ConiumEventArgTypes.SERVER_WORLD]
    val scheduledTickView: ScheduledTickView = this.context[ConiumEventArgTypes.SCHEDULED_TICK_VIEW]
    val fluid: Fluid = this.context.identity as Fluid
    val blockPos: BlockPos = this.context[ConiumEventArgTypes.BLOCK_POS]
    val blockState: BlockState = this.context[ConiumEventArgTypes.BLOCK_STATE]
    val fluidState: FluidState = this.context[ConiumEventArgTypes.FLUID_STATE]
}