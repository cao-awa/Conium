package com.github.cao.awa.conium.item.event.use.block

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ITEM_USAGE_CONTEXT
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.WORLD
import com.github.cao.awa.conium.event.type.ConiumEventType
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.world.World

class ConiumItemUseOnBlockEventMetadata(val context: ConiumEventContext<Item>) : ConiumEventMetadata<Item>() {
    val world: World = this.context[WORLD]
    val itemUsageContext: ItemUsageContext = this.context[ITEM_USAGE_CONTEXT]
}
