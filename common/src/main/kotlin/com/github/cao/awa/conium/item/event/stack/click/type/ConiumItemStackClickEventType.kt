package com.github.cao.awa.conium.item.event.stack.click.type

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import com.github.cao.awa.conium.item.event.stack.click.metadata.ConiumItemStackClickEventMetadata
import com.github.cao.awa.conium.item.event.stack.clicked.metadata.ConiumItemStackClickedEventMetadata
import com.github.cao.awa.conium.item.event.use.metadata.ConiumItemUseEventMetadata
import com.github.cao.awa.conium.item.event.used.metadata.ConiumItemUsedEventMetadata
import com.github.cao.awa.conium.item.event.used.type.ConiumItemUsedEventType
import net.minecraft.item.Item

class ConiumItemStackClickEventType: ConiumCancelableEventType<Item, ConiumItemStackClickEventMetadata, Item, ConiumItemStackClickedEventMetadata>(
    "item_stack_click",
    "Item",
    ConiumEvent.Companion::itemStackClick
) {
}