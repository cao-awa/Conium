package com.github.cao.awa.conium.entity.event.rest.sleep.type

import com.github.cao.awa.conium.blockentity.event.shulker.opened.metadata.ConiumShulkerBoxOpenedEventMetadata
import com.github.cao.awa.conium.blockentity.event.shulker.opening.metadata.ConiumShulkerBoxOpeningEventMetadata
import com.github.cao.awa.conium.entity.event.rest.sleep.metadata.ConiumEntitySleepEventMetadata
import com.github.cao.awa.conium.entity.event.rest.sleep.metadata.ConiumEntityTrySleepEventMetadata
import com.github.cao.awa.conium.entity.event.rest.wake.metadata.ConiumEntityWakeUpEventMetadata
import com.github.cao.awa.conium.entity.event.rest.waked.metadata.ConiumEntityWakedUpEventMetadata
import com.github.cao.awa.conium.entity.event.sprint.metadata.ConiumEntitySprintEventMetadata
import com.github.cao.awa.conium.entity.event.sprinting.metadata.ConiumEntitySprintingEventMetadata
import com.github.cao.awa.conium.entity.event.sprinting.stop.metadata.ConiumEntityStopSprintEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import net.minecraft.block.Block
import net.minecraft.entity.EntityType

class ConiumEntityTrySleepEventType: ConiumCancelableEventType<EntityType<*>, ConiumEntityTrySleepEventMetadata, EntityType<*>, ConiumEntitySleepEventMetadata>(
    "entity_try_sleep",
    "EntityType",
    ConiumEvent.Companion::entityTrySleep
)