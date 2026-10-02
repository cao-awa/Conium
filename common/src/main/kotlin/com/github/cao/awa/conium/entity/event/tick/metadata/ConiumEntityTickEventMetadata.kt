package com.github.cao.awa.conium.entity.event.tick.metadata
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType

class ConiumEntityTickEventMetadata(val context: ConiumEventContext<EntityType<*>>) : ConiumEventMetadata<EntityType<*>, ConiumEntityTickEventMetadata>() {
    val entity: Entity = this.context[ConiumEventArgTypes.ENTITY]
}