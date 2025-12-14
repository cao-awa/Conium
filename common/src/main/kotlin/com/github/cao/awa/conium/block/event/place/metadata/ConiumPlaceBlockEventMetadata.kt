package com.github.cao.awa.conium.block.event.place.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.block.Block
import net.minecraft.item.ItemPlacementContext

class ConiumPlaceBlockEventMetadata(val context: ConiumEventContext<Block>) : ConiumEventMetadata<Block, ConiumPlaceBlockEventMetadata>() {
    val block: Block = this.context.identity as Block
    val itemPlacementContext: ItemPlacementContext = this.context[ConiumEventArgTypes.ITEM_PLACEMENT_CONTEXT]
}