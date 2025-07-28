package com.github.cao.awa.conium.mixin.craft.table;

import com.github.cao.awa.conium.intermediary.craft.table.ConiumCraftingEventMixinIntermediary;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.CrafterOutputSlot;
import net.minecraft.screen.slot.CraftingResultSlot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingResultSlot.class)
public class CraftingResultSlotMixin {
    @Shadow @Final private PlayerEntity player;

    @Inject(
            method = "onCrafted(Lnet/minecraft/item/ItemStack;I)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onCrafted(ItemStack stack, int i, CallbackInfo ci) {
        if (ConiumCraftingEventMixinIntermediary.firePlayerCraftingItemEvent(this.player, stack)) {
            ci.cancel();
        }
    }

    @Inject(
            method = "onCrafted(Lnet/minecraft/item/ItemStack;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;onCraftByPlayer(Lnet/minecraft/entity/player/PlayerEntity;I)V"
            )
    )
    public void onCrafted(ItemStack stack, CallbackInfo ci) {
        ConiumCraftingEventMixinIntermediary.firePlayerCraftedItemEvent(this.player, stack);
    }
}
