package com.github.cao.awa.conium.item.event.use.block.on.used.metadata
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.InteractionResult
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level

class ConiumItemUsedOnBlockEventMetadata(val context: ConiumEventContext<Item>) : ConiumEventMetadata<Item, ConiumItemUsedOnBlockEventMetadata>() {
    val itemUsageContext: UseOnContext = this.context[ConiumEventArgTypes.ITEM_USAGE_CONTEXT]
    val world: World get() = this.itemUsageContext.level
    val player: Player? get() = this.itemUsageContext.player
    val blockPos: BlockPos get() = this.itemUsageContext.clickedPos
    val blockState: BlockState get() = this.world.getBlockState(this.blockPos)
    val stack: ItemStack get() = this.itemUsageContext.itemInHand
    val actionResult: InteractionResult = this.context[ConiumEventArgTypes.ACTION_RESULT]
}
