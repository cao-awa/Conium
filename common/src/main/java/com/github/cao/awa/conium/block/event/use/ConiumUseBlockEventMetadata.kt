package com.github.cao.awa.conium.block.event.use

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ACTION_RESULT
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_ENTITY
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_HIT_RESULT
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_POS
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.PLAYER
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.VIEWER_COUNT_MANAGER
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.WORLD
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_STATE
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ITEM_STACK
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.LIVING_ENTITY
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.ViewerCountManager
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumUseBlockEventMetadata(val context: ConiumEventContext) : ConiumEventMetadata() {
    val world: World = this.context[WORLD]
    val user: PlayerEntity = this.context[PLAYER]
    val blockPos: BlockPos = this.context[BLOCK_POS]
    val blockState: BlockState = this.context[BLOCK_STATE]
    val hitResult: BlockHitResult = this.context[BLOCK_HIT_RESULT]
}
