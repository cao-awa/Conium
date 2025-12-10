package com.github.cao.awa.conium

import com.github.cao.awa.conium.feature.ConiumFeatureRegister
import com.github.cao.awa.conium.feature.ConiumFabricFeatureRegister
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager

class ConiumFabric : ModInitializer {
    companion object {
        private val LOGGER = LogManager.getLogger("ConiumFabric")
    }

    override fun onInitialize() {
        LOGGER.info("Conium fabric bootstrap running")

        Conium().onInitialize()

        ConiumFeatureRegister.IMPL = ConiumFabricFeatureRegister()
    }
}
