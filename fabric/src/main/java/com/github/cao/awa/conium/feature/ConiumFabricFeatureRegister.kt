package com.github.cao.awa.conium.feature

import com.github.cao.awa.conium.Conium
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.world.level.levelgen.GenerationStep
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ConiumFabricFeatureRegister : ConiumFeatureRegister() {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumFeatureRegister")
    }

    private val modifiers: MutableSet<Identifier> = HashSet()

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
            val registryKey = ResourceKey.create(Registries.PLACED_FEATURE, id)
            BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, registryKey)

            this.modifiers.add(id)
        }

        Conium.debug(
            "Registered feature: {}",
            { id },
            LOGGER::info
        )
    }
}
