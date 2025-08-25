package com.github.cao.awa.conium.datapack.worldgen

import com.github.cao.awa.conium.datapack.ConiumJsonDataLoader
import com.github.cao.awa.conium.feature.ConiumFeatureRegister
import com.github.cao.awa.conium.kotlin.extend.equals
import com.github.cao.awa.conium.registry.ConiumRegistryKeys
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler

class ConiumPlacedFeatureManager(var registryLookup: RegistryWrapper.WrapperLookup) : ConiumJsonDataLoader(ConiumRegistryKeys.PLACED_FEATURE.value) {
    override fun earlyLoad(manager: ResourceManager, dataType: Identifier, result: MutableMap<Identifier, JsonElement>) {
        for ((identifier: Identifier, _: JsonElement) in result) {
            if (identifier.namespace equals "minecraft") {
                continue
            }
            val path: String = identifier.path.run {
                substring(lastIndexOf("/") + 1)
            }.run {
                substring(0, indexOf("."))
            }

            ConiumFeatureRegister.IMPL.placedFeature(Identifier.of(identifier.namespace, path))
        }
    }

    override fun apply(prepared: MutableMap<Identifier, JsonElement>, manager: ResourceManager, profiler: Profiler) {
        // Nothing here.
    }
}