package com.github.cao.awa.conium.item.event.use.type

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.item.event.use.metadata.ConiumItemUseEventMetadata
import com.github.cao.awa.conium.item.event.used.metadata.ConiumItemUsedEventMetadata
import com.github.cao.awa.conium.item.event.used.type.ConiumItemUsedEventType
import net.minecraft.item.Item

class ConiumItemUseEventType: ConiumCancelableEventType<Item, ConiumItemUseEventMetadata, Item, ConiumItemUsedEventMetadata>(
    "item_use",
    "Item",
    ConiumEvent.Companion::itemUse
) {
}