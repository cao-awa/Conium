package com.github.cao.awa.conium.item.event.use.entity.on.used.type

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.item.event.use.entity.on.used.metadata.ConiumItemUsedOnEntityEventMetadata
import com.github.cao.awa.conium.item.event.use.entity.on.use.metadata.ConiumItemUseOnEntityEventMetadata
import com.github.cao.awa.conium.item.event.use.metadata.ConiumItemUseEventMetadata
import com.github.cao.awa.conium.item.event.use.usage.tick.metadata.ConiumItemUsageTickEventMetadata
import com.github.cao.awa.conium.item.event.use.usage.ticked.metadata.ConiumItemUsageTickedEventMetadata
import com.github.cao.awa.conium.item.event.used.metadata.ConiumItemUsedEventMetadata
import net.minecraft.item.Item

class ConiumItemUsedOnEntityEventType: ConiumNoCancelableEventType<Item, ConiumItemUsedOnEntityEventMetadata, Unit, ConiumInactiveEventMetadata>(
    "item_used_on_entity",
    "Item",
    ConiumEvent.Companion::itemUsedOnEntity
)