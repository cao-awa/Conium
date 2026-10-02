package com.github.cao.awa.conium.mixin.block.entity.furnace;

import com.github.cao.awa.conium.Conium;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {
    @Inject(
            method = "getBurnDuration",
            at = @At("RETURN"),
            cancellable = true
    )
    protected void getBurnDuration(ServerLevel level, ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (cir.getReturnValue() == 0) {
            assert Conium.coniumItemManager != null;
            cir.setReturnValue(Conium.coniumItemManager.getFuelTicks(stack));
        }
    }
}
