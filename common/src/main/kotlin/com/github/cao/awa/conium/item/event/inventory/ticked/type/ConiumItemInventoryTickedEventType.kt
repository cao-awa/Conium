package com.github.cao.awa.conium.item.event.inventory.ticked.type

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import com.github.cao.awa.conium.item.event.inventory.ticked.metadata.ConiumItemInventoryTickedEventMetadata
import com.github.cao.awa.conium.item.event.use.metadata.ConiumItemUseEventMetadata
import com.github.cao.awa.conium.item.event.used.metadata.ConiumItemUsedEventMetadata
import com.github.cao.awa.conium.item.event.used.type.ConiumItemUsedEventType
import net.minecraft.item.Item

class ConiumItemInventoryTickedEventType: ConiumCancelableEventType<Item, ConiumItemInventoryTickedEventMetadata, Unit, ConiumInactiveEventMetadata>(
    "item_inventory_ticked",
    "Item",
    ConiumEvent.Companion::itemInventoryTicked
) {
}