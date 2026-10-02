package com.github.cao.awa.conium.block.event.place.metadata
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.world.level.block.Block
import net.minecraft.world.item.context.BlockPlaceContext

class ConiumPlaceBlockEventMetadata(val context: ConiumEventContext<Block>) : ConiumEventMetadata<Block, ConiumPlaceBlockEventMetadata>() {
    val block: Block = this.context.identity as Block
    val itemPlacementContext: BlockPlaceContext = this.context[ConiumEventArgTypes.ITEM_PLACEMENT_CONTEXT]
}