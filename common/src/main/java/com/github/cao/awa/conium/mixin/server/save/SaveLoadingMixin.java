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
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.RegistryLayer;
import net.minecraft.server.packs.resources.CloseableResourceManager;
import net.minecraft.server.WorldLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WorldLoader.class)
public class SaveLoadingMixin {
    @WrapOperation(
            method = "lambda$load$0",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/RegistryLayer;createRegistryAccess()Lnet/minecraft/core/LayeredRegistryAccess;"
            )
    )
    private static LayeredRegistryAccess<RegistryLayer> loadDynamic(
            Operation<LayeredRegistryAccess<RegistryLayer>> original,
            @Local CloseableResourceManager closeableResourceManager
    ) {
        LayeredRegistryAccess<RegistryLayer> registries = original.call();
        RegistryAccess registryManager = registries.getAccessForLoading(RegistryLayer.RELOADABLE);

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
