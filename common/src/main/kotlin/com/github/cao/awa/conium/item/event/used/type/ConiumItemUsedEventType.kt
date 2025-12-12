package com.github.cao.awa.conium.item.event.used.type

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.item.event.use.metadata.ConiumItemUseEventMetadata
import com.github.cao.awa.conium.item.event.used.metadata.ConiumItemUsedEventMetadata
import net.minecraft.item.Item

class ConiumItemUsedEventType: ConiumNoCancelableEventType<Item, ConiumItemUsedEventMetadata, Unit, ConiumInactiveEventMetadata>(
    "item_used",
    "Item",
    ConiumEvent.Companion::itemUsed
)