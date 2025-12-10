package com.github.cao.awa.conium

import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.IEventBus
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent

/**
 * Not supported yet now.
 */
@Mod("conium", dist = [Dist.CLIENT, Dist.DEDICATED_SERVER])
class ConiumNeoForged(bus: IEventBus) {
    init {
        Conium().onInitialize()

        NeoForge.EVENT_BUS.register(this)
    }

    @SubscribeEvent
    fun register(event: RegisterPayloadHandlersEvent) {
    }
}
