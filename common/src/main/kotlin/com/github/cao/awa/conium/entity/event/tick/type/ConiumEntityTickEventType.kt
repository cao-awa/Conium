package com.github.cao.awa.conium.entity.event.tick.type
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.blockentity.event.shulker.opened.metadata.ConiumShulkerBoxOpenedEventMetadata
import com.github.cao.awa.conium.entity.event.tick.metadata.ConiumEntityTickEventMetadata
import com.github.cao.awa.conium.entity.event.ticked.metadata.ConiumEntityTickedEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import net.minecraft.world.level.block.Block
import net.minecraft.world.entity.EntityType

class ConiumEntityTickEventType: ConiumNoCancelableEventType<EntityType<*>, ConiumEntityTickEventMetadata, EntityType<*>, ConiumEntityTickedEventMetadata>(
    "entity_tick",
    "EntityType",
    ConiumEvent.Companion::entityTick
)