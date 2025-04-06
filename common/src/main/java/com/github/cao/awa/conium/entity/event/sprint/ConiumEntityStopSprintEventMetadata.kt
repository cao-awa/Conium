package com.github.cao.awa.conium.entity.event.sprint

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ENTITY
import net.minecraft.entity.Entity

class ConiumEntityStopSprintEventMetadata(val context: ConiumEventContext) : ConiumEventMetadata() {
    val entity: Entity = this.context[ENTITY]
}
