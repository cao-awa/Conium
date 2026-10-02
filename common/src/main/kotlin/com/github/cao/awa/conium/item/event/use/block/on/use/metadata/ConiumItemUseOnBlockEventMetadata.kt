package com.github.cao.awa.conium.item.event.use.block.on.use.metadata
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.world.item.Item
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.Level

class ConiumItemUseOnBlockEventMetadata(val context: ConiumEventContext<Item>) : ConiumEventMetadata<Item, ConiumItemUseOnBlockEventMetadata>() {
    val world: World = this.context[ConiumEventArgTypes.WORLD]
    val itemUsageContext: UseOnContext = this.context[ConiumEventArgTypes.ITEM_USAGE_CONTEXT]
}