package com.github.cao.awa.conium.mixin.item.food;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Optional;

@Mixin(FoodComponent.Builder.class)
public interface FoodComponentBuilderAccessor {
    @Accessor
    void setUsingConvertsTo(Optional<ItemStack> stack);
}
