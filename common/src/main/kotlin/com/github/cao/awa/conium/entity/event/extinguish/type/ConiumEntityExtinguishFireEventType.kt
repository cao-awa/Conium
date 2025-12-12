package com.github.cao.awa.conium.entity.event.extinguish.type

import com.github.cao.awa.conium.entity.event.dead.metadata.ConiumEntityDeadEventMetadata
import com.github.cao.awa.conium.entity.event.die.metadata.ConiumEntityDieEventMetadata
import com.github.cao.awa.conium.entity.event.extinguish.metadata.ConiumEntityExtinguishFireEventMetadata
import com.github.cao.awa.conium.entity.event.extinguished.metadata.ConiumEntityExtinguishedFireEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import net.minecraft.entity.EntityType

class ConiumEntityExtinguishFireEventType: ConiumCancelableEventType<EntityType<*>, ConiumEntityExtinguishFireEventMetadata, EntityType<*>, ConiumEntityExtinguishedFireEventMetadata>(
    "entity_extinguish_fire",
    "EntityType",
    ConiumEvent.Companion::entityExtinguishFire
)