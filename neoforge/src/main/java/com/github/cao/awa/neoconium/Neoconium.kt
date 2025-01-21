package com.github.cao.awa.neoconium

import com.github.cao.awa.conium.Conium
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent

@Mod("neoconium", dist = [Dist.CLIENT, Dist.DEDICATED_SERVER])
class Neoconium {
    @SubscribeEvent
    fun register(event: RegisterPayloadHandlersEvent) {
        Conium().onInitialize()
    }
}
