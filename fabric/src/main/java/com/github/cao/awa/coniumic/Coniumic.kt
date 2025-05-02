package com.github.cao.awa.coniumic

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.feature.ConiumFeatureRegister
import com.github.cao.awa.coniumic.feature.ConiumFabricFeatureRegister
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager

class Coniumic : ModInitializer {
    companion object {
        private val LOGGER = LogManager.getLogger("Coniumic")
    }

    override fun onInitialize() {
        LOGGER.info("Conium fabric bootstrap running")

        Conium().onInitialize()

        ConiumFeatureRegister.IMPL = ConiumFabricFeatureRegister()
    }
}
