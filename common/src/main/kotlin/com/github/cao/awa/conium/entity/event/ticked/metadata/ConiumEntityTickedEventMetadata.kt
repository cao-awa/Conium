package com.github.cao.awa.conium.entity.event.ticked.metadata
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType

class ConiumEntityTickedEventMetadata(val context: ConiumEventContext<EntityType<*>>) : ConiumEventMetadata<EntityType<*>, ConiumEntityTickedEventMetadata>() {
    val entity: Entity = this.context[ConiumEventArgTypes.ENTITY]
}