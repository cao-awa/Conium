package com.github.cao.awa.conium.item.event.use.block.on.use.type

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.item.event.use.block.on.used.metadata.ConiumItemUsedOnBlockEventMetadata
import com.github.cao.awa.conium.item.event.use.block.on.use.metadata.ConiumItemUseOnBlockEventMetadata
import com.github.cao.awa.conium.item.event.use.metadata.ConiumItemUseEventMetadata
import com.github.cao.awa.conium.item.event.used.metadata.ConiumItemUsedEventMetadata
import com.github.cao.awa.conium.item.event.used.type.ConiumItemUsedEventType
import net.minecraft.item.Item

class ConiumItemUseOnBlockEventType: ConiumCancelableEventType<Item, ConiumItemUseOnBlockEventMetadata, Item, ConiumItemUsedOnBlockEventMetadata>(
    "item_use_on_block",
    "Item",
    ConiumEvent.Companion::itemUseOnBlock
) {
}