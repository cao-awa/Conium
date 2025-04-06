package com.github.cao.awa.conium.entity.event.fire.extinguish

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ENTITY
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.INT
import net.minecraft.entity.Entity

class ConiumEntityExtinguishFireEventMetadata(val context: ConiumEventContext) : ConiumEventMetadata() {
    val entity: Entity = this.context[ENTITY]
    val leftFireTicks: Int = this.context[INT]
}
