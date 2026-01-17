package com.github.cao.awa.conium.mixin.block.compostable;

import net.minecraft.block.ComposterBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ComposterBlock.class)
public interface ComposterBlockAccessor {
    @Invoker("registerCompostableItem")
    static void invokeRegisterCompostableItem(float chance, ItemConvertible item) {
        throw new AssertionError();
    }
}
