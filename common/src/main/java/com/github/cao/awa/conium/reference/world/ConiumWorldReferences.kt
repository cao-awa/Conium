package com.github.cao.awa.conium.reference.world

import net.minecraft.client.world.ClientWorld as ClientWorldSource
import net.minecraft.server.world.ServerWorld as ServerWorldSource
import net.minecraft.world.World as WorldSource
import net.minecraft.world.tick.ScheduledTickView as ScheduledTickViewSource

typealias World = WorldSource
typealias ClientWorld = ClientWorldSource
typealias ServerWorld = ServerWorldSource
typealias ScheduledTickView = ScheduledTickViewSource