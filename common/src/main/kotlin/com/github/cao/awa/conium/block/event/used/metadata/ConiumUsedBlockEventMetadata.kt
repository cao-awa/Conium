package com.github.cao.awa.conium.block.event.used.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumUsedBlockEventMetadata(val context: ConiumEventContext<Block>) : ConiumEventMetadata<Block, ConiumUsedBlockEventMetadata>() {
    val world: World = this.context[ConiumEventArgTypes.WORLD]
    val user: PlayerEntity = this.context[ConiumEventArgTypes.PLAYER]
    val blockPos: BlockPos = this.context[ConiumEventArgTypes.BLOCK_POS]
    val blockState: BlockState = this.context[ConiumEventArgTypes.BLOCK_STATE]
    val hitResult: BlockHitResult = this.context[ConiumEventArgTypes.BLOCK_HIT_RESULT]
    val actionResult: ActionResult = this.context[ConiumEventArgTypes.ACTION_RESULT]
}