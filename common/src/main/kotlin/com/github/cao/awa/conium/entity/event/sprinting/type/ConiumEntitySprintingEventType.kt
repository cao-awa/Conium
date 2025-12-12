package com.github.cao.awa.conium.entity.event.sprinting.type

import com.github.cao.awa.conium.blockentity.event.shulker.opened.metadata.ConiumShulkerBoxOpenedEventMetadata
import com.github.cao.awa.conium.blockentity.event.shulker.opening.metadata.ConiumShulkerBoxOpeningEventMetadata
import com.github.cao.awa.conium.entity.event.sprinting.metadata.ConiumEntitySprintingEventMetadata
import com.github.cao.awa.conium.entity.event.sprinting.stop.metadata.ConiumEntityStopSprintEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import net.minecraft.block.Block
import net.minecraft.entity.EntityType

class ConiumEntitySprintingEventType: ConiumCancelableEventType<EntityType<*>, ConiumEntitySprintingEventMetadata, EntityType<*>, ConiumEntityStopSprintEventMetadata>(
    "entity_sprinting",
    "EntityType",
    ConiumEvent.Companion::entitySprinting
)