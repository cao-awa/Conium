package com.github.cao.awa.conium.block.event.fluid.schedule.tick.metadata
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.mapping.yarn.ScheduledTickView
import com.github.cao.awa.conium.mapping.yarn.ServerLevel
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import net.minecraft.core.BlockPos

class ConiumFluidScheduleTickEventMetadata(val context: ConiumEventContext<Fluid>) : ConiumEventMetadata<Fluid, ConiumFluidScheduleTickEventMetadata>() {
    val serverWorld: ServerLevel = this.context[ConiumEventArgTypes.SERVER_WORLD]
    val scheduledTickView: ScheduledTickView = this.context[ConiumEventArgTypes.SCHEDULED_TICK_VIEW]
    val fluid: Fluid = this.context.identity as Fluid
    val blockPos: BlockPos = this.context[ConiumEventArgTypes.BLOCK_POS]
    val blockState: BlockState = this.context[ConiumEventArgTypes.BLOCK_STATE]
    val fluidState: FluidState = this.context[ConiumEventArgTypes.FLUID_STATE]
}