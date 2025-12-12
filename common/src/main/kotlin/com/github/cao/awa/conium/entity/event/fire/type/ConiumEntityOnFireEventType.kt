package com.github.cao.awa.conium.entity.event.fire.type

import com.github.cao.awa.conium.blockentity.event.shulker.opened.metadata.ConiumShulkerBoxOpenedEventMetadata
import com.github.cao.awa.conium.blockentity.event.shulker.opening.metadata.ConiumShulkerBoxOpeningEventMetadata
import com.github.cao.awa.conium.entity.event.dead.metadata.ConiumEntityDeadEventMetadata
import com.github.cao.awa.conium.entity.event.die.metadata.ConiumEntityDieEventMetadata
import com.github.cao.awa.conium.entity.event.fire.metadata.ConiumEntityOnFireEventMetadata
import com.github.cao.awa.conium.entity.event.rest.sleep.metadata.ConiumEntitySleepEventMetadata
import com.github.cao.awa.conium.entity.event.rest.sleep.metadata.ConiumEntityTrySleepEventMetadata
import com.github.cao.awa.conium.entity.event.rest.wake.metadata.ConiumEntityWakeUpEventMetadata
import com.github.cao.awa.conium.entity.event.rest.waked.metadata.ConiumEntityWakedUpEventMetadata
import com.github.cao.awa.conium.entity.event.sprint.metadata.ConiumEntitySprintEventMetadata
import com.github.cao.awa.conium.entity.event.sprinting.metadata.ConiumEntitySprintingEventMetadata
import com.github.cao.awa.conium.entity.event.sprinting.stop.metadata.ConiumEntityStopSprintEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import net.minecraft.block.Block
import net.minecraft.entity.EntityType

class ConiumEntityOnFireEventType: ConiumCancelableEventType<EntityType<*>, ConiumEntityOnFireEventMetadata, EntityType<*>, ConiumEntityDeadEventMetadata>(
    "entity_on_fire",
    "EntityType",
    ConiumEvent.Companion::entityOnFire
)