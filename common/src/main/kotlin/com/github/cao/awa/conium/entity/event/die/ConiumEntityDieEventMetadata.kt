package com.github.cao.awa.conium.entity.event.die

import com.github.cao.awa.conium.event.context.ConiumEventContext
import net.minecraft.entity.EntityType

class ConiumEntityDieEventMetadata(context: ConiumEventContext<EntityType<*>>) : ConiumEntityDeathsEventMetadata(context)
