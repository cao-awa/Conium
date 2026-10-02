package com.github.cao.awa.conium.mixin.screen;

import com.github.cao.awa.conium.Conium;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceMenu.class)
public class AbstractFurnaceMenuMixin {
    @Inject(
            method = "isFuel",
            at = @At("RETURN"),
            cancellable = true
    )
    private void isFuel(ItemStack item, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            assert Conium.coniumItemManager != null;
            cir.setReturnValue(Conium.coniumItemManager.getFuels().contains(item.getItem()));
        }
    }
}
