package com.github.cao.awa.conium.entity.event.extinguished.type

import com.github.cao.awa.conium.entity.event.dead.metadata.ConiumEntityDeadEventMetadata
import com.github.cao.awa.conium.entity.event.die.metadata.ConiumEntityDieEventMetadata
import com.github.cao.awa.conium.entity.event.extinguish.metadata.ConiumEntityExtinguishFireEventMetadata
import com.github.cao.awa.conium.entity.event.extinguished.metadata.ConiumEntityExtinguishedFireEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import net.minecraft.entity.EntityType

class ConiumEntityExtinguishedFireEventType: ConiumNoCancelableEventType<EntityType<*>, ConiumEntityExtinguishedFireEventMetadata, EntityType<*>, ConiumEntityExtinguishedFireEventMetadata>(
    "entity_extinguished_fire",
    "EntityType",
    ConiumEvent.Companion::entityExtinguishedFire
)