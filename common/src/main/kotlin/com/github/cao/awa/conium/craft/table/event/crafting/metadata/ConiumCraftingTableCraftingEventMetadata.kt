package com.github.cao.awa.conium.craft.table.event.crafting.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

class ConiumCraftingTableCraftingEventMetadata(val context: ConiumEventContext<Item>) : ConiumEventMetadata<Item, ConiumCraftingTableCraftingEventMetadata>() {
    val resultStack: ItemStack = this.context[ConiumEventArgTypes.ITEM_STACK]
}