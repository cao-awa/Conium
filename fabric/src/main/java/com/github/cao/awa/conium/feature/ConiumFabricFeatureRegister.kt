package com.github.cao.awa.conium.feature

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import net.minecraft.world.gen.GenerationStep
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ConiumFabricFeatureRegister : ConiumFeatureRegister() {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumFeatureRegister")
    }

    private val modifiers: MutableSet<Identifier> = CollectionFactor.hashSet()

    //  TODO Remove fabric api
    override fun placedFeature(id: Identifier?) {
        if (id == null) {
            LOGGER.warn("Cannot register null identifier to the feature registry", NullPointerException("Null identifier"))
            return
        }

        if (id.namespace.equals("minecraft")) {
            LOGGER.warn("Cannot register null identifier to the feature registry", NullPointerException("Null identifier"))
        }

        if (!this.modifiers.contains(id)) {
            val registryKey = RegistryKey.of(RegistryKeys.PLACED_FEATURE, id)
            BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, registryKey)

            this.modifiers.add(id)
        }

        Conium.debug(
            "Registered feature: {}",
            { id },
            LOGGER::info
        )
    }
}
