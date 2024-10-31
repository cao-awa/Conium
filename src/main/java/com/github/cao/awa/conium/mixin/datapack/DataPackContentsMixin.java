package com.github.cao.awa.conium.mixin.datapack;

import com.github.cao.awa.conium.Conium;
import com.github.cao.awa.conium.datapack.block.ConiumBlockManager;
import com.github.cao.awa.conium.datapack.inject.item.ItemPropertyInjectManager;
import com.github.cao.awa.conium.datapack.item.ConiumItemManager;
import com.github.cao.awa.conium.datapack.script.ConiumScriptManager;
import com.github.cao.awa.conium.mixin.recipe.RecipeManagerAccessor;
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor;
import net.minecraft.recipe.ServerRecipeManager;
import net.minecraft.registry.CombinedDynamicRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.ServerDynamicRegistryType;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.server.DataPackContents;
import net.minecraft.server.command.CommandManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(DataPackContents.class)
public class DataPackContentsMixin {
    @Shadow
    @Final
    private ServerRecipeManager recipeManager;
    @Unique
    private ItemPropertyInjectManager itemPropertyInjectManager;
    @Unique
    private ConiumItemManager coniumItemManager;
    @Unique
    private ConiumBlockManager coniumBlockManager;
    @Unique
    private ConiumScriptManager scriptManager;

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    public void init(CombinedDynamicRegistries<ServerDynamicRegistryType> dynamicRegistries, RegistryWrapper.WrapperLookup registries, FeatureSet enabledFeatures, CommandManager.RegistrationEnvironment environment, List<Registry.PendingTagLoad<?>> pendingTagLoads, int functionPermissionLevel, CallbackInfo ci) {
        RegistryWrapper.WrapperLookup lookup = ((RecipeManagerAccessor) this.recipeManager).getRegistries();
        this.itemPropertyInjectManager = new ItemPropertyInjectManager();
        this.coniumItemManager = new ConiumItemManager(lookup, pendingTagLoads);
        this.coniumBlockManager = new ConiumBlockManager(lookup);
        this.scriptManager = new ConiumScriptManager();
        Conium.itemInjectManager = this.itemPropertyInjectManager;
        Conium.coniumItemManager = this.coniumItemManager;
        Conium.coniumBlockManager = this.coniumBlockManager;
        Conium.scriptManager = this.scriptManager;
    }

    @Inject(
            method = "getContents",
            at = @At("RETURN"),
            cancellable = true
    )
    public void contents(CallbackInfoReturnable<List<ResourceReloader>> cir) {
        List<ResourceReloader> reloaderList = CollectionFactor.arrayList(cir.getReturnValue());
        reloaderList.add(this.itemPropertyInjectManager);
        reloaderList.add(this.coniumItemManager);
        reloaderList.add(this.coniumBlockManager);
        reloaderList.add(this.scriptManager);
        cir.setReturnValue(reloaderList);
    }
}
