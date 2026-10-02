package com.github.cao.awa.conium.entity.event.sprint.metadata
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType

class ConiumEntitySprintEventMetadata(val context: ConiumEventContext<EntityType<*>>) : ConiumEventMetadata<EntityType<*>, ConiumEntitySprintEventMetadata>() {
    val entity: Entity = this.context[ConiumEventArgTypes.ENTITY]
}