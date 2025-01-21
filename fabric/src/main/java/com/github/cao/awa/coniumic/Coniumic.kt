package com.github.cao.awa.coniumic

import com.github.cao.awa.conium.Conium
import net.fabricmc.api.ModInitializer

class Coniumic : ModInitializer {
    override fun onInitialize() {
        Conium().onInitialize()
    }
}
