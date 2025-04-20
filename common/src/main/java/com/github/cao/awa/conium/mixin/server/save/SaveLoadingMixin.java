package com.github.cao.awa.conium.mixin.server.save;

import com.github.cao.awa.conium.Conium;
import com.github.cao.awa.conium.datapack.block.ConiumBlockManager;
import com.github.cao.awa.conium.datapack.entity.ConiumEntityManager;
import com.github.cao.awa.conium.datapack.inject.item.ItemPropertyInjectManager;
import com.github.cao.awa.conium.datapack.item.ConiumItemManager;
import com.github.cao.awa.conium.datapack.script.ConiumScriptManager;
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
        DynamicRegistryManager registryManager = registries.getPrecedingRegistryManagers(ServerDynamicRegistryType.STATIC);

        Conium.itemInjectManager = new ItemPropertyInjectManager();
        Conium.coniumItemManager = new ConiumItemManager();
        Conium.coniumBlockManager = new ConiumBlockManager();
        Conium.coniumEntityManager = new ConiumEntityManager();
        Conium.placedFeatureManager = new ConiumPlacedFeatureManager();
        Conium.scriptManager  = new ConiumScriptManager();

        Conium.coniumItemManager.earlyPrepare(closeableResourceManager, registryManager);
        Conium.coniumBlockManager.earlyPrepare(closeableResourceManager, registryManager);
        Conium.coniumEntityManager.earlyPrepare(closeableResourceManager, registryManager);
        Conium.placedFeatureManager.earlyPrepare(closeableResourceManager, registryManager);

        return registries;
    }
}
