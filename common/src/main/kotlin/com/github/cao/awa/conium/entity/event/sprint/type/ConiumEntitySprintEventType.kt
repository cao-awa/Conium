package com.github.cao.awa.conium.entity.event.sprint.type

import com.github.cao.awa.conium.blockentity.event.shulker.opened.metadata.ConiumShulkerBoxOpenedEventMetadata
import com.github.cao.awa.conium.blockentity.event.shulker.opening.metadata.ConiumShulkerBoxOpeningEventMetadata
import com.github.cao.awa.conium.entity.event.sprint.metadata.ConiumEntitySprintEventMetadata
import com.github.cao.awa.conium.entity.event.sprinting.metadata.ConiumEntitySprintingEventMetadata
import com.github.cao.awa.conium.entity.event.sprinting.stop.metadata.ConiumEntityStopSprintEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import net.minecraft.block.Block
import net.minecraft.entity.EntityType

class ConiumEntitySprintEventType: ConiumCancelableEventType<EntityType<*>, ConiumEntitySprintEventMetadata, EntityType<*>, ConiumEntitySprintingEventMetadata>(
    "entity_sprint",
    "EntityType",
    ConiumEvent.Companion::entitySprint
)