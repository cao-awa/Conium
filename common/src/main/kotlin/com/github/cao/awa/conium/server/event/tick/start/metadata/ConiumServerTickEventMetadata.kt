package com.github.cao.awa.conium.server.event.tick.start.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.server.MinecraftServer

class ConiumServerTickEventMetadata(val context: ConiumEventContext<MinecraftServer>) : ConiumEventMetadata<MinecraftServer, ConiumServerTickEventMetadata>() {
    val server: MinecraftServer = this.context[ConiumEventArgTypes.SERVER]
}