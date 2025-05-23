package com.github.cao.awa.conium.block.event.tick.fluid

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_POS
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_STATE
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.FLUID_STATE
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.SCHEDULED_TICK_VIEW
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.RANDOM
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.SERVER_WORLD
import com.github.cao.awa.conium.mapping.yarn.ScheduledTickView
import com.github.cao.awa.conium.mapping.yarn.ServerWorld
import net.minecraft.block.BlockState
import net.minecraft.fluid.FluidState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random

class ConiumFluidScheduleTickedEventMetadata(val context: ConiumEventContext) : ConiumEventMetadata() {
    val serverWorld: ServerWorld = this.context[SERVER_WORLD]
    val scheduledTickView: ScheduledTickView = this.context[SCHEDULED_TICK_VIEW]
    val blockPos: BlockPos = this.context[BLOCK_POS]
    val blockState: BlockState = this.context[BLOCK_STATE]
    val fluidState: FluidState = this.context[FLUID_STATE]
}
