package com.github.cao.awa.conium.server.event.random.type

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.empty.ConiumEmptyEventMetadata
import com.github.cao.awa.conium.event.type.cancelable.ConiumNoCancelableEventType
import com.github.cao.awa.conium.server.event.random.metadata.ConiumServerRandomEventMetadata
import net.minecraft.server.MinecraftServer

class ConiumServerRandomEventType: ConiumNoCancelableEventType<MinecraftServer, ConiumServerRandomEventMetadata, MinecraftServer, ConiumServerRandomEventMetadata>(
    "server_random",
    "MinecraftServer",
    ConiumEvent.Companion::serverRandom
) {
}