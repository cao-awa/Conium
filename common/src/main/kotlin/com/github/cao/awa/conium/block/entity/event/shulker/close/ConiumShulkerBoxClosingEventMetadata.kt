package com.github.cao.awa.conium.block.entity.event.shulker.close

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_ENTITY
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_POS
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.PLAYER
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.WORLD
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_STATE
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumShulkerBoxClosingEventMetadata(val context: ConiumEventContext) : ConiumEventMetadata() {
    val world: World = this.context[WORLD]
    val player: PlayerEntity = this.context[PLAYER]
    val blockEntity: BlockEntity = this.context[BLOCK_ENTITY]
    val blockState: BlockState = this.context[BLOCK_STATE]
    val blockPos: BlockPos = this.context[BLOCK_POS]
}
