package com.github.cao.awa.conium.entity.event.sprinting.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType

class ConiumEntitySprintingEventMetadata(val context: ConiumEventContext<EntityType<*>>) : ConiumEventMetadata<EntityType<*>, ConiumEntitySprintingEventMetadata>() {
    val entity: Entity = this.context[ConiumEventArgTypes.ENTITY]

}