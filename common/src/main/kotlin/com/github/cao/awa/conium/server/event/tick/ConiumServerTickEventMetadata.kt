package com.github.cao.awa.conium.server.event.tick

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.server.MinecraftServer

class ConiumServerTickEventMetadata(val context: ConiumEventContext) : ConiumEventMetadata() {
    val server: MinecraftServer = this.context[ConiumEventArgTypes.SERVER]
}