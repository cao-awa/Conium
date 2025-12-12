package com.github.cao.awa.conium.craft.table.event.crafted.type

import com.github.cao.awa.conium.chunk.event.received.metadata.ConiumReceivedChunkEventMetadata
import com.github.cao.awa.conium.craft.table.event.crafted.metadata.ConiumCraftingTableCraftedEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import net.minecraft.item.Item
import net.minecraft.world.chunk.WorldChunk

class ConiumCraftingTableCraftedEventType: ConiumCancelableEventType<Item, ConiumCraftingTableCraftedEventMetadata, Unit, ConiumInactiveEventMetadata>(
    "crafting_table_crafted",
    "Item",
    ConiumEvent.Companion::craftingTableCrafted
)