package com.github.cao.awa.conium.server.event.random

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import net.minecraft.server.MinecraftServer

class ConiumServerRandomEventMetadata(val context: ConiumEventContext<MinecraftServer>) : ConiumEventMetadata<MinecraftServer>()
