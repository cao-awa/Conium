package com.github.cao.awa.conium.item.event.use.block.on.use.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.world.World

class ConiumItemUseOnBlockEventMetadata(val context: ConiumEventContext<Item>) : ConiumEventMetadata<Item, ConiumItemUseOnBlockEventMetadata>() {
    val world: World = this.context[ConiumEventArgTypes.WORLD]
    val itemUsageContext: ItemUsageContext = this.context[ConiumEventArgTypes.ITEM_USAGE_CONTEXT]
}