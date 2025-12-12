package com.github.cao.awa.conium.random.event.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import net.minecraft.util.Unit

class ConiumRandomEventMetadata(val context: ConiumEventContext<Unit>) : ConiumEventMetadata<Unit, ConiumRandomEventMetadata>()