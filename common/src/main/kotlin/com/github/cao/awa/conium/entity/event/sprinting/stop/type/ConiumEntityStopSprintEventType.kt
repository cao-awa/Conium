package com.github.cao.awa.conium.entity.event.sprinting.stop.type

import com.github.cao.awa.conium.blockentity.event.shulker.opened.metadata.ConiumShulkerBoxOpenedEventMetadata
import com.github.cao.awa.conium.blockentity.event.shulker.opening.metadata.ConiumShulkerBoxOpeningEventMetadata
import com.github.cao.awa.conium.entity.event.sprinting.stop.metadata.ConiumEntityStopSprintEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import net.minecraft.block.Block
import net.minecraft.entity.EntityType

class ConiumEntityStopSprintEventType: ConiumCancelableEventType<EntityType<*>, ConiumEntityStopSprintEventMetadata, Unit, ConiumInactiveEventMetadata>(
    "entity_stop_sprint",
    "EntityType",
    ConiumEvent.Companion::entityStopSprint
)