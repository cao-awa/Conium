package com.github.cao.awa.conium.entity.event.ticked.type

import com.github.cao.awa.conium.blockentity.event.chest.trapped.closed.metadata.ConiumTrappedChestClosedEventMetadata
import com.github.cao.awa.conium.blockentity.event.chest.trapped.closing.metadata.ConiumTrappedChestClosingEventMetadata
import com.github.cao.awa.conium.blockentity.event.shulker.opened.metadata.ConiumShulkerBoxOpenedEventMetadata
import com.github.cao.awa.conium.blockentity.event.shulker.opening.metadata.ConiumShulkerBoxOpeningEventMetadata
import com.github.cao.awa.conium.entity.event.ticked.metadata.ConiumEntityTickedEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import net.minecraft.block.Block
import net.minecraft.entity.EntityType

class ConiumEntityTickedEventType: ConiumNoCancelableEventType<EntityType<*>, ConiumEntityTickedEventMetadata, Unit, ConiumInactiveEventMetadata>(
    "entity_tick_ticked",
    "EntityType",
    ConiumEvent.Companion::entityTicked
)