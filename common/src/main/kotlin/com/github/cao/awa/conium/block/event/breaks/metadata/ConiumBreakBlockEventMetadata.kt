package com.github.cao.awa.conium.block.event.breaks.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.block.Block
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumBreakBlockEventMetadata(val context: ConiumEventContext<Block>) : ConiumEventMetadata<Block, ConiumBreakBlockEventMetadata>() {
    val world: World = this.context[ConiumEventArgTypes.WORLD]
    val player: PlayerEntity = this.context[ConiumEventArgTypes.PLAYER]
    val blockPos: BlockPos = this.context[ConiumEventArgTypes.BLOCK_POS]
}