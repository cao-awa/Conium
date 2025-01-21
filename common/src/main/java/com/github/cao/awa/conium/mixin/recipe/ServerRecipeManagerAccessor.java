package com.github.cao.awa.conium.mixin.recipe;

import net.minecraft.recipe.PreparedRecipes;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.ServerRecipeManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.resource.featuretoggle.FeatureSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;
import java.util.Map;

@Mixin(ServerRecipeManager.class)
public interface ServerRecipeManagerAccessor {
    @Invoker("collectServerRecipes")
    static List<ServerRecipeManager.ServerRecipe> collectServerRecipes(Iterable<RecipeEntry<?>> recipes, FeatureSet enabledFeatures) {
        throw new AssertionError();
    }

    @Accessor
    RegistryWrapper.WrapperLookup getRegistries();

    @Accessor("preparedRecipes")
    PreparedRecipes getPreparedRecipes();

    @Accessor("recipes")
    List<ServerRecipeManager.ServerRecipe> getRecipes();

    @Accessor("recipesByKey")
    Map<RegistryKey<Recipe<?>>, List<ServerRecipeManager.ServerRecipe>> getRecipesByKey();
}
