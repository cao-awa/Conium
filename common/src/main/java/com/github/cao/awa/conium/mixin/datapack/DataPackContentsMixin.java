package com.github.cao.awa.conium.mixin.datapack;

import com.github.cao.awa.conium.Conium;
import com.github.cao.awa.conium.datapack.recipe.ConiumRecipeManager;
import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.server.ConiumDedicatedServer;
import net.minecraft.commands.Commands;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentInitializers;
import net.minecraft.server.RegistryLayer;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.permissions.PermissionSet;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.crafting.RecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(ReloadableServerResources.class)
public abstract class DataPackContentsMixin {
    @Inject(
            method = "loadResources",
            at = @At("RETURN")
    )
    private static void reload(
            ResourceManager resourceManager,
            LayeredRegistryAccess<RegistryLayer> dynamicRegistries,
            List<Registry.PendingTags<?>> pendingTagLoads,
            FeatureFlagSet enabledFeatures,
            Commands.CommandSelection environment,
            PermissionSet permissions,
            Executor prepareExecutor,
            Executor applyExecutor,
            CallbackInfoReturnable<CompletableFuture<ReloadableServerResources>> cir
    ) {
        if (ConiumDedicatedServer.isInitialized()) {
            ConiumDedicatedServer.onReload();
        }
        ConiumEvent.clearAll();

        // Do callbacks when completed reloading.
        cir.getReturnValue().whenCompleteAsync(((dataPackContents, throwable) -> {
            for (Runnable reloadCallback : Conium.reloadCallbacks) {
                reloadCallback.run();
            }
        }));
    }

    @Shadow
    public abstract List<PreparableReloadListener> listeners();

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    public void init(
            ReloadableServerRegistries.LoadResult loadResult,
            FeatureFlagSet enabledFeatures,
            Commands.CommandSelection environment,
            List<Registry.PendingTags<?>> pendingTagLoads,
            PermissionSet permissions,
            List<DataComponentInitializers.PendingComponents<?>> pendingComponents,
            CallbackInfo ci
    ) {
        assert Conium.coniumItemManager != null;
        Conium.coniumItemManager.setPendingTagLoad(pendingTagLoads);
    }

    @Redirect(
            method = "<init>",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/world/item/crafting/RecipeManager;"
            )
    )
    public RecipeManager delegateRecipes(HolderLookup.Provider registries) {
        return new ConiumRecipeManager(registries);
    }

    @Inject(
            method = "listeners",
            at = @At("RETURN"),
            cancellable = true
    )
    public void contents(CallbackInfoReturnable<List<PreparableReloadListener>> cir) {
        List<PreparableReloadListener> reloaderList = new ArrayList<>(cir.getReturnValue());
        reloaderList.add(Conium.itemInjectManager);
        reloaderList.add(Conium.coniumItemManager);
        reloaderList.add(Conium.coniumBlockManager);
        reloaderList.add(Conium.coniumEntityManager);
        reloaderList.add(Conium.placedFeatureManager);
        reloaderList.add(Conium.scriptManager);
        cir.setReturnValue(reloaderList);
    }
}
