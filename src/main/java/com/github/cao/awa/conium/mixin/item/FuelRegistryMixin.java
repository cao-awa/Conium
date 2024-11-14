package com.github.cao.awa.conium.mixin.item;

import com.github.cao.awa.conium.Conium;
import net.minecraft.item.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.SequencedSet;

@Mixin(FuelRegistry.class)
public class FuelRegistryMixin {
    @Inject(
            method = "isFuel",
            at = @At("RETURN"),
            cancellable = true
    )
    public void isFuel(ItemStack item, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            // When the item are not registered fuel in vanilla, the find the fuel in conium registry.
            assert Conium.coniumItemManager != null;
            cir.setReturnValue(Conium.coniumItemManager.getFuels().contains(item.getItem()));
        }
    }

    @Inject(
            method = "getFuelItems",
            at = @At("RETURN"),
            cancellable = true
    )
    public void getFuelItems(CallbackInfoReturnable<SequencedSet<Item>> cir) {
        // When the item are not registered fuel in vanilla, the find the fuel in conium registry.
        assert Conium.coniumItemManager != null;
        cir.setReturnValue(Conium.coniumItemManager.computeFuels(cir.getReturnValue()));
    }

    @Inject(
            method = "getFuelTicks",
            at = @At("RETURN"),
            cancellable = true
    )
    public void getFuelTicks(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (cir.getReturnValue() == 0) {
            // When the item are not registered fuel in vanilla, the find the fuel ticks in conium registry.
            assert Conium.coniumItemManager != null;
            cir.setReturnValue(Conium.coniumItemManager.getFuelTicks(stack));
        }
    }
}
