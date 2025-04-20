package com.github.cao.awa.conium.mixin.datapack;

import com.github.cao.awa.conium.Conium;
import com.github.cao.awa.conium.datapack.block.ConiumBlockManager;
import com.github.cao.awa.conium.datapack.entity.ConiumEntityManager;
import com.github.cao.awa.conium.datapack.inject.item.ItemPropertyInjectManager;
import com.github.cao.awa.conium.datapack.item.ConiumItemManager;
import com.github.cao.awa.conium.datapack.recipe.ConiumRecipeManager;
import com.github.cao.awa.conium.datapack.script.ConiumScriptManager;
import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.mixin.recipe.ServerRecipeManagerAccessor;
import com.github.cao.awa.conium.server.ConiumDedicatedServer;
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor;
import net.minecraft.recipe.ServerRecipeManager;
import net.minecraft.registry.*;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.server.DataPackContents;
import net.minecraft.server.command.CommandManager;
import net.minecraft.util.Unit;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(DataPackContents.class)
public abstract class DataPackContentsMixin {
    @Shadow
    @Final
    private static CompletableFuture<Unit> COMPLETED_UNIT;
    @Shadow
    @Final
    private static Logger LOGGER;
    @Shadow
    @Final
    private ServerRecipeManager recipeManager;

    @Inject(method = "reload", at = @At(value = "RETURN"))
    private static void reload(
            ResourceManager resourceManager,
            CombinedDynamicRegistries<ServerDynamicRegistryType> dynamicRegistries,
            List<Registry.PendingTagLoad<?>> pendingTagLoads,
            FeatureSet enabledFeatures,
            CommandManager.RegistrationEnvironment environment,
            int functionPermissionLevel,
            Executor prepareExecutor,
            Executor applyExecutor,
            CallbackInfoReturnable<CompletableFuture<DataPackContents>> cir
    ) {
        if (ConiumDedicatedServer.isInitialized()) {
            ConiumDedicatedServer.onReload();
        }

        ConiumEvent.clearAll();

        // Do callbacks when completed reloading.
        cir.getReturnValue().whenComplete(((dataPackContents, throwable) -> {
            for (Runnable reloadCallback : Conium.reloadCallbacks) {
                reloadCallback.run();
            }
        }));
    }

    @Shadow
    public abstract List<ResourceReloader> getContents();

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    public void init(CombinedDynamicRegistries<ServerDynamicRegistryType> dynamicRegistries, RegistryWrapper.WrapperLookup registries, FeatureSet enabledFeatures, CommandManager.RegistrationEnvironment environment, List<Registry.PendingTagLoad<?>> pendingTagLoads, int functionPermissionLevel, CallbackInfo ci) {
        RegistryWrapper.WrapperLookup lookup = ((ServerRecipeManagerAccessor) this.recipeManager).getRegistries();
        Conium.itemInjectManager = new ItemPropertyInjectManager();
//        Conium.coniumItemManager = new ConiumItemManager();
        assert Conium.coniumItemManager != null;
        Conium.coniumItemManager.setRegistryLookup(lookup);
        Conium.coniumItemManager.setPendingTagLoad(pendingTagLoads);
//        Conium.coniumBlockManager = new ConiumBlockManager();
        assert Conium.coniumBlockManager != null;
        Conium.coniumBlockManager.setRegistryLookup(lookup);
//        Conium.coniumEntityManager = new ConiumEntityManager();
        assert Conium.coniumEntityManager != null;
        Conium.coniumEntityManager.setRegistryLookup(lookup);
//        Conium.scriptManager = new ConiumScriptManager();
    }

    @Redirect(
            method = "<init>",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;)Lnet/minecraft/recipe/ServerRecipeManager;"
            )
    )
    public ServerRecipeManager delegateRecipes(RegistryWrapper.WrapperLookup registries) {
        return new ConiumRecipeManager(registries);
    }

    @Inject(
            method = "getContents",
            at = @At("RETURN"),
            cancellable = true
    )
    public void contents(CallbackInfoReturnable<List<ResourceReloader>> cir) {
        List<ResourceReloader> reloaderList = CollectionFactor.arrayList(cir.getReturnValue());
        reloaderList.add(Conium.itemInjectManager);
        reloaderList.add(Conium.coniumItemManager);
        reloaderList.add(Conium.coniumBlockManager);
        reloaderList.add(Conium.coniumEntityManager);
        reloaderList.add(Conium.placedFeatureManager);
        reloaderList.add(Conium.scriptManager);
        cir.setReturnValue(reloaderList);
    }
}
