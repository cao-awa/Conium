package com.github.cao.awa.conium.mixin.renderer.item;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemModels.class)
public abstract class ItemModelsAccessor {
    @Shadow
    public abstract void putModel(Item item, ModelIdentifier modelId);

    @Shadow
    @Final
    private Int2ObjectMap<BakedModel> models;

    @Shadow
    @Final
    public Int2ObjectMap<ModelIdentifier> modelIds;

    @Inject(
            method = "reloadModels",
            at = @At("HEAD")
    )
    public void reloadModels(CallbackInfo ci) {
        this.modelIds.clear();

        Registries.ITEM.iterator().forEachRemaining(item -> {
            if (item != Items.AIR) {
                putModel(item, ModelIdentifier.ofInventoryVariant(Registries.ITEM.getId(item)));
            }
        });
    }
}
