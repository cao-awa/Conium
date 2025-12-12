package com.github.cao.awa.conium.entity.event.rest.wake.type

import com.github.cao.awa.conium.blockentity.event.shulker.opened.metadata.ConiumShulkerBoxOpenedEventMetadata
import com.github.cao.awa.conium.blockentity.event.shulker.opening.metadata.ConiumShulkerBoxOpeningEventMetadata
import com.github.cao.awa.conium.entity.event.rest.wake.metadata.ConiumEntityWakeUpEventMetadata
import com.github.cao.awa.conium.entity.event.rest.waked.metadata.ConiumEntityWakedUpEventMetadata
import com.github.cao.awa.conium.entity.event.sprint.metadata.ConiumEntitySprintEventMetadata
import com.github.cao.awa.conium.entity.event.sprinting.metadata.ConiumEntitySprintingEventMetadata
import com.github.cao.awa.conium.entity.event.sprinting.stop.metadata.ConiumEntityStopSprintEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import net.minecraft.block.Block
import net.minecraft.entity.EntityType

class ConiumEntityWakeUpEventType: ConiumCancelableEventType<EntityType<*>, ConiumEntityWakeUpEventMetadata, EntityType<*>, ConiumEntityWakedUpEventMetadata>(
    "entity_wake_up",
    "EntityType",
    ConiumEvent.Companion::entityWakeUp
)