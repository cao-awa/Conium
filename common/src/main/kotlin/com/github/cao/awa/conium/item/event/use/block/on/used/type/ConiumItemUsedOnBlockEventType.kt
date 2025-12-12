package com.github.cao.awa.conium.item.event.use.block.on.used.type

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.item.event.use.block.on.used.metadata.ConiumItemUsedOnBlockEventMetadata
import com.github.cao.awa.conium.item.event.use.block.on.use.metadata.ConiumItemUseOnBlockEventMetadata
import com.github.cao.awa.conium.item.event.use.metadata.ConiumItemUseEventMetadata
import com.github.cao.awa.conium.item.event.used.metadata.ConiumItemUsedEventMetadata
import com.github.cao.awa.conium.item.event.used.type.ConiumItemUsedEventType
import net.minecraft.item.Item

class ConiumItemUsedOnBlockEventType: ConiumCancelableEventType<Item, ConiumItemUsedOnBlockEventMetadata, Unit, ConiumInactiveEventMetadata>(
    "item_used_on_block",
    "Item",
    ConiumEvent.Companion::itemUsedOnBlock
) {
}