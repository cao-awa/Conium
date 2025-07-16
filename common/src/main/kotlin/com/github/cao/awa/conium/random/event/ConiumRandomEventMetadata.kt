package com.github.cao.awa.conium.random.event

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import net.minecraft.util.Unit as MinecraftUnit

class ConiumRandomEventMetadata(val context: ConiumEventContext<MinecraftUnit>) : ConiumEventMetadata<MinecraftUnit>()
