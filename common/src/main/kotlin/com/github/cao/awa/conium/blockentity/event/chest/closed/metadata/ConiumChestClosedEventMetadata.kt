package com.github.cao.awa.conium.blockentity.event.chest.closed.metadata
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.ContainerOpenersCounter
import net.minecraft.world.entity.player.Player
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level

class ConiumChestClosedEventMetadata(val context: ConiumEventContext<Block>) : ConiumEventMetadata<Block, ConiumChestClosedEventMetadata>() {
    val world: World = this.context[ConiumEventArgTypes.WORLD]
    val player: Player = this.context[ConiumEventArgTypes.PLAYER]
    val blockEntity: BlockEntity = this.context[ConiumEventArgTypes.BLOCK_ENTITY]
    val blockState: BlockState = this.context[ConiumEventArgTypes.BLOCK_STATE]
    val blockPos: BlockPos = this.context[ConiumEventArgTypes.BLOCK_POS]
    val viewerCountManager: ContainerOpenersCounter = this.context[ConiumEventArgTypes.VIEWER_COUNT_MANAGER]
}