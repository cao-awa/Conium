package com.github.cao.awa.conium.block.event.schedule.tick.metadata
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.mapping.yarn.ScheduledTickView
import com.github.cao.awa.conium.mapping.yarn.ServerLevel
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource

class ConiumBlockScheduleTickEventMetadata(val context: ConiumEventContext<Block>) : ConiumEventMetadata<Block, ConiumBlockScheduleTickEventMetadata>() {
    val serverWorld: ServerLevel = this.context[ConiumEventArgTypes.SERVER_WORLD]
    val scheduledTickView: ScheduledTickView = this.context[ConiumEventArgTypes.SCHEDULED_TICK_VIEW]
    val block: Block = this.context.identity as Block
    val blockPos: BlockPos = this.context[ConiumEventArgTypes.BLOCK_POS]
    val blockState: BlockState = this.context[ConiumEventArgTypes.BLOCK_STATE]
    val random: Random = this.context[ConiumEventArgTypes.RANDOM]
}