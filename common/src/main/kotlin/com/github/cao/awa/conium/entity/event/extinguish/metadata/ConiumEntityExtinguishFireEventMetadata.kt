package com.github.cao.awa.conium.entity.event.extinguish.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType

class ConiumEntityExtinguishFireEventMetadata(val context: ConiumEventContext<EntityType<*>>) : ConiumEventMetadata<EntityType<*>, ConiumEntityExtinguishFireEventMetadata>() {
    val entity: Entity = this.context[ConiumEventArgTypes.ENTITY]
    val leftFireTicks: Int = this.context[ConiumEventArgTypes.INT]
}