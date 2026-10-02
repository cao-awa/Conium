package com.github.cao.awa.conium.datapack.worldgen

import com.github.cao.awa.conium.datapack.ConiumJsonDataLoader
import com.github.cao.awa.conium.feature.ConiumFeatureRegister
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.google.gson.JsonElement
import net.minecraft.core.RegistryAccess
import net.minecraft.core.HolderLookup
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.resources.Identifier
import net.minecraft.util.profiling.ProfilerFiller

class ConiumPlacedFeatureManager(var registryLookup: HolderLookup.Provider) : ConiumJsonDataLoader(ConiumRegistryKeys.PLACED_FEATURE.identifier()) {
    override fun earlyLoad(manager: ResourceManager, dataType: Identifier, result: MutableMap<Identifier, JsonElement>) {
        for ((identifier: Identifier, _: JsonElement) in result) {
            if (identifier.namespace.equals("minecraft")) {
                continue
            }
            val path: String = identifier.path.run {
                substring(lastIndexOf("/") + 1)
            }.run {
                substring(0, indexOf("."))
            }

            ConiumFeatureRegister.IMPL.placedFeature(Identifier.fromNamespaceAndPath(identifier.namespace, path))
        }
    }

    override fun apply(prepared: MutableMap<Identifier, JsonElement>, manager: ResourceManager, profiler: ProfilerFiller) {
        // Nothing here.
    }
}
