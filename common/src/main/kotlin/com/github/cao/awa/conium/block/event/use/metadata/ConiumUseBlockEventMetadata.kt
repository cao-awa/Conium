package com.github.cao.awa.conium.block.event.use.metadata
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.entity.player.Player
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level

class ConiumUseBlockEventMetadata(val context: ConiumEventContext<Block>) : ConiumEventMetadata<Block, ConiumUseBlockEventMetadata>() {
    val world: World = this.context[ConiumEventArgTypes.WORLD]
    val user: Player = this.context[ConiumEventArgTypes.PLAYER]
    val block: Block = this.context.identity as Block
    val blockPos: BlockPos = this.context[ConiumEventArgTypes.BLOCK_POS]
    val blockState: BlockState = this.context[ConiumEventArgTypes.BLOCK_STATE]
    val hitResult: BlockHitResult = this.context[ConiumEventArgTypes.BLOCK_HIT_RESULT]
}