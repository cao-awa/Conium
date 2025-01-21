package com.github.cao.awa.neoconium

import com.github.cao.awa.conium.Conium
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.IEventBus
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent

/**
 * Not supported yet now.
 */
@Mod("neoconium", dist = [Dist.CLIENT, Dist.DEDICATED_SERVER])
class Neoconium(bus: IEventBus) {
    init {
        Conium().onInitialize()

        bus.register(this)
    }

    @SubscribeEvent
    fun register(event: RegisterPayloadHandlersEvent) {
    }
}
