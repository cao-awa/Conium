package com.github.cao.awa.conium.server.event.tick.start.type

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.server.event.tick.start.metadata.ConiumServerTickEventMetadata
import net.minecraft.server.MinecraftServer

class ConiumServerTickEventType: ConiumCancelableEventType<MinecraftServer, ConiumServerTickEventMetadata, MinecraftServer, ConiumServerTickEventMetadata>(
    "server_tick",
    "MinecraftServer",
    ConiumEvent.Companion::serverTick
)