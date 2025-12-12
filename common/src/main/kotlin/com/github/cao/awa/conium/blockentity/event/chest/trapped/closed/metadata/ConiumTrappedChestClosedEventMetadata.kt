package com.github.cao.awa.conium.blockentity.event.chest.trapped.closed.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.ViewerCountManager
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumTrappedChestClosedEventMetadata(val context: ConiumEventContext<Block>) : ConiumEventMetadata<Block, ConiumTrappedChestClosedEventMetadata>() {
    val world: World = this.context[ConiumEventArgTypes.WORLD]
    val player: PlayerEntity = this.context[ConiumEventArgTypes.PLAYER]
    val blockEntity: BlockEntity = this.context[ConiumEventArgTypes.BLOCK_ENTITY]
    val blockState: BlockState = this.context[ConiumEventArgTypes.BLOCK_STATE]
    val blockPos: BlockPos = this.context[ConiumEventArgTypes.BLOCK_POS]
    val viewerCountManager: ViewerCountManager = this.context[ConiumEventArgTypes.VIEWER_COUNT_MANAGER]
}