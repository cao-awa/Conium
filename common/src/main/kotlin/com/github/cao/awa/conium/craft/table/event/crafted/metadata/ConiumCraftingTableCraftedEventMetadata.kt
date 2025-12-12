package com.github.cao.awa.conium.craft.table.event.crafted.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

class ConiumCraftingTableCraftedEventMetadata(val context: ConiumEventContext<Item>) : ConiumEventMetadata<Item, ConiumCraftingTableCraftedEventMetadata>() {
    val resultStack: ItemStack = this.context[ConiumEventArgTypes.ITEM_STACK]
}