package com.github.cao.awa.conium.entity.event.extinguished.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType

class ConiumEntityExtinguishedFireEventMetadata(val context: ConiumEventContext<EntityType<*>>) : ConiumEventMetadata<EntityType<*>, ConiumEntityExtinguishedFireEventMetadata>() {
    val entity: Entity = this.context[ConiumEventArgTypes.ENTITY]
}