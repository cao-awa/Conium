package com.github.cao.awa.conium.mixin.server.save;

import com.github.cao.awa.conium.Conium;
import com.github.cao.awa.conium.datapack.block.ConiumBlockManager;
import com.github.cao.awa.conium.datapack.entity.ConiumEntityManager;
import com.github.cao.awa.conium.datapack.inject.item.ConiumItemPropertyInjectManager;
import com.github.cao.awa.conium.datapack.item.ConiumItemManager;
import com.github.cao.awa.conium.script.manager.ConiumScriptManager;
import com.github.cao.awa.conium.datapack.worldgen.ConiumPlacedFeatureManager;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.registry.CombinedDynamicRegistries;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.ServerDynamicRegistryType;
import net.minecraft.resource.LifecycledResourceManager;
import net.minecraft.server.SaveLoading;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SaveLoading.class)
public class SaveLoadingMixin {
    @WrapOperation(
            method = "load",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/registry/ServerDynamicRegistryType;createCombinedDynamicRegistries()Lnet/minecraft/registry/CombinedDynamicRegistries;"
            )
    )
    private static CombinedDynamicRegistries<ServerDynamicRegistryType> loadDynamic(
            Operation<CombinedDynamicRegistries<ServerDynamicRegistryType>> original,
            @Local LifecycledResourceManager closeableResourceManager
    ) {
        CombinedDynamicRegistries<ServerDynamicRegistryType> registries = original.call();
        DynamicRegistryManager registryManager = registries.getPrecedingRegistryManagers(ServerDynamicRegistryType.RELOADABLE);

        Conium.itemInjectManager = new ConiumItemPropertyInjectManager(registryManager);
        Conium.coniumItemManager = new ConiumItemManager(registryManager);
        Conium.coniumBlockManager = new ConiumBlockManager(registryManager);
        Conium.coniumEntityManager = new ConiumEntityManager(registryManager);
        Conium.placedFeatureManager = new ConiumPlacedFeatureManager(registryManager);
        Conium.scriptManager = new ConiumScriptManager(registryManager);

        Conium.coniumItemManager.earlyPrepare(closeableResourceManager);
        Conium.coniumBlockManager.earlyPrepare(closeableResourceManager);
        Conium.coniumEntityManager.earlyPrepare(closeableResourceManager);
        Conium.placedFeatureManager.earlyPrepare(closeableResourceManager);

        return registries;
    }
}
