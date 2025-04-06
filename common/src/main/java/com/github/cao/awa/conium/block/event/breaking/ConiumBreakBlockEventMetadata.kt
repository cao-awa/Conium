package com.github.cao.awa.conium.block.event.breaking

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_ENTITY
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_POS
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.PLAYER
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.VIEWER_COUNT_MANAGER
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.WORLD
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_STATE
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.ViewerCountManager
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumBreakBlockEventMetadata(val context: ConiumEventContext) : ConiumEventMetadata() {
    val world: World = this.context[WORLD]
    val player: PlayerEntity = this.context[PLAYER]
    val blockPos: BlockPos = this.context[BLOCK_POS]
}
