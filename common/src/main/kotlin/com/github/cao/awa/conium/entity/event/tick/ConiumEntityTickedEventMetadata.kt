package com.github.cao.awa.conium.entity.event.tick

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ENTITY
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType

class ConiumEntityTickedEventMetadata(val context: ConiumEventContext<EntityType<*>>) : ConiumEventMetadata<EntityType<*>>() {
    val entity: Entity = this.context[ENTITY]
}
