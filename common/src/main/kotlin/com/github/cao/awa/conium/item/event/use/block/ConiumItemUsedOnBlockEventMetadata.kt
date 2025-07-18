package com.github.cao.awa.conium.item.event.use.block

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ACTION_RESULT
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ITEM_USAGE_CONTEXT
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.ActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumItemUsedOnBlockEventMetadata(val context: ConiumEventContext<Item>) : ConiumEventMetadata<Item>() {
    val itemUsageContext: ItemUsageContext = this.context[ITEM_USAGE_CONTEXT]
    val world: World get() = this.itemUsageContext.world
    val player: PlayerEntity? get() = this.itemUsageContext.player
    val blockPos: BlockPos get() = this.itemUsageContext.blockPos
    val blockState: BlockState get() = this.world.getBlockState(this.blockPos)
    val stack: ItemStack get() = this.itemUsageContext.stack
    val actionResult: ActionResult = this.context[ACTION_RESULT]
}
