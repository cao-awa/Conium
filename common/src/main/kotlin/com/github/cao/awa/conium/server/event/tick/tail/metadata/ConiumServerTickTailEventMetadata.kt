package com.github.cao.awa.conium.server.event.tick.tail.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import net.minecraft.server.MinecraftServer

class ConiumServerTickTailEventMetadata(val context: ConiumEventContext<MinecraftServer>) : ConiumEventMetadata<MinecraftServer, ConiumServerTickTailEventMetadata>()