package com.github.cao.awa.conium.block.event.placed.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumPlacedBlockEventMetadata(val context: ConiumEventContext<Block>) : ConiumEventMetadata<Block, ConiumPlacedBlockEventMetadata>() {
    val world: World = this.context[ConiumEventArgTypes.WORLD]
    val placer: LivingEntity = this.context[ConiumEventArgTypes.LIVING_ENTITY]
    val blockPos: BlockPos = this.context[ConiumEventArgTypes.BLOCK_POS]
    val blockState: BlockState = this.context[ConiumEventArgTypes.BLOCK_STATE]
    val itemStack: ItemStack = this.context[ConiumEventArgTypes.ITEM_STACK]
}