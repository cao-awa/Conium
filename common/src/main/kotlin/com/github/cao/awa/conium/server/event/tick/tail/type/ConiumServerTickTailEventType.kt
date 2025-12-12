package com.github.cao.awa.conium.server.event.tick.tail.type

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.random.event.metadata.ConiumRandomEventMetadata
import com.github.cao.awa.conium.server.event.tick.tail.metadata.ConiumServerTickTailEventMetadata
import com.github.cao.awa.conium.server.event.tick.start.metadata.ConiumServerTickEventMetadata
import net.minecraft.server.MinecraftServer
import net.minecraft.util.Unit

class ConiumServerTickTailEventType: ConiumNoCancelableEventType<MinecraftServer, ConiumServerTickTailEventMetadata, MinecraftServer, ConiumServerTickTailEventMetadata>(
    "server_tick_tail",
    "MinecraftServer",
    ConiumEvent.Companion::serverTickTail
)