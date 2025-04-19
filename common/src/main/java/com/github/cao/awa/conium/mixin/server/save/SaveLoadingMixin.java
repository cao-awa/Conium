package com.github.cao.awa.conium.mixin.server.save;

import com.github.cao.awa.conium.Conium;
import com.github.cao.awa.conium.datapack.block.ConiumBlockManager;
import com.github.cao.awa.conium.datapack.entity.ConiumEntityManager;
import com.github.cao.awa.conium.datapack.inject.item.ItemPropertyInjectManager;
import com.github.cao.awa.conium.datapack.item.ConiumItemManager;
import com.github.cao.awa.conium.datapack.script.ConiumScriptManager;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.registry.CombinedDynamicRegistries;
import net.minecraft.registry.ServerDynamicRegistryType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.resource.LifecycledResourceManager;
import net.minecraft.server.SaveLoading;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(SaveLoading.class)
public class SaveLoadingMixin {
    @Inject(
            method = "load",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/registry/ServerDynamicRegistryType;createCombinedDynamicRegistries()Lnet/minecraft/registry/CombinedDynamicRegistries;",
                    shift = At.Shift.AFTER
            )
    )
    private static <D, R> void loadDynamic(
            SaveLoading.ServerConfig serverConfig,
            SaveLoading.LoadContextSupplier<D> loadContextSupplier,
            SaveLoading.SaveApplierFactory<D, R> saveApplierFactory,
            Executor prepareExecutor,
            Executor applyExecutor,
            CallbackInfoReturnable<CompletableFuture<R>> cir,
            @Local LifecycledResourceManager closeableResourceManager
    ) {
        Conium.itemInjectManager = new ItemPropertyInjectManager();
        Conium.coniumItemManager = new ConiumItemManager();
        Conium.coniumBlockManager = new ConiumBlockManager();
        Conium.coniumEntityManager = new ConiumEntityManager();
        Conium.scriptManager  = new ConiumScriptManager();

        Conium.coniumItemManager.earlyPrepare(closeableResourceManager);
        Conium.coniumBlockManager.earlyPrepare(closeableResourceManager);
        Conium.coniumEntityManager.earlyPrepare(closeableResourceManager);
    }
}
