package com.github.cao.awa.conium.block.event.breaking.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumBreakingBlockEventMetadata(val context: ConiumEventContext<Block>) : ConiumEventMetadata<Block, ConiumBreakingBlockEventMetadata>() {
    val world: World = this.context[ConiumEventArgTypes.WORLD]
    val player: PlayerEntity = this.context[ConiumEventArgTypes.PLAYER]
    val block: Block = this.context.identity as Block
    val blockPos: BlockPos = this.context[ConiumEventArgTypes.BLOCK_POS]
    val blockState: BlockState = this.context[ConiumEventArgTypes.BLOCK_STATE]
}