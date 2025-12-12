package com.github.cao.awa.conium.entity.event.damaged.type

import com.github.cao.awa.conium.blockentity.event.shulker.opened.metadata.ConiumShulkerBoxOpenedEventMetadata
import com.github.cao.awa.conium.blockentity.event.shulker.opening.metadata.ConiumShulkerBoxOpeningEventMetadata
import com.github.cao.awa.conium.entity.event.damaged.metadata.ConiumEntityDamagedEventMetadata
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

class ConiumEntityDamagedEventType: ConiumNoCancelableEventType<EntityType<*>, ConiumEntityDamagedEventMetadata, Unit, ConiumInactiveEventMetadata>(
    "entity_damaged",
    "EntityType",
    ConiumEvent.Companion::entityDamaged
)