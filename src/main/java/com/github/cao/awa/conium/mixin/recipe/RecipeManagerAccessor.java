package com.github.cao.awa.conium.mixin.recipe;

import com.google.common.collect.Multimap;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RecipeManager.class)
public interface RecipeManagerAccessor {
    @Accessor
    Multimap<RecipeType<?>, RecipeEntry<?>> getRecipesByType();

    @Accessor
    RegistryWrapper.WrapperLookup getRegistryLookup();
}
