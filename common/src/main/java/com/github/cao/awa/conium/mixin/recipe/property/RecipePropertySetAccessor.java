package com.github.cao.awa.conium.mixin.recipe.property;

import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipePropertySet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Collection;

@Mixin(RecipePropertySet.class)
public interface RecipePropertySetAccessor {
    @Invoker("of")
    static RecipePropertySet of(Collection<Ingredient> ingredients) {
        throw new AssertionError();
    }
}
