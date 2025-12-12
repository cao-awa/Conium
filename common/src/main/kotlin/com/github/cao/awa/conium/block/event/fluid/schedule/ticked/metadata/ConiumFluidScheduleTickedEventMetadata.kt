package com.github.cao.awa.conium.block.event.fluid.schedule.ticked.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.mapping.yarn.ScheduledTickView
import com.github.cao.awa.conium.mapping.yarn.ServerWorld
import net.minecraft.block.BlockState
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.util.math.BlockPos

class ConiumFluidScheduleTickedEventMetadata(val context: ConiumEventContext<Fluid>) : ConiumEventMetadata<Fluid, ConiumFluidScheduleTickedEventMetadata>() {
    val serverWorld: ServerWorld = this.context[ConiumEventArgTypes.SERVER_WORLD]
    val scheduledTickView: ScheduledTickView = this.context[ConiumEventArgTypes.SCHEDULED_TICK_VIEW]
    val blockPos: BlockPos = this.context[ConiumEventArgTypes.BLOCK_POS]
    val blockState: BlockState = this.context[ConiumEventArgTypes.BLOCK_STATE]
    val fluidState: FluidState = this.context[ConiumEventArgTypes.FLUID_STATE]
}