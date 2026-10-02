package com.github.cao.awa.conium.mixin.craft.table;

import com.github.cao.awa.conium.intermediary.craft.table.ConiumCraftingEventMixinIntermediary;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.ResultSlot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ResultSlot.class)
public class CraftingResultSlotMixin {
    @Shadow @Final private Player player;

    @Inject(
            method = "onQuickCraft(Lnet/minecraft/world/item/ItemStack;I)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onCrafted(ItemStack stack, int i, CallbackInfo ci) {
        if (ConiumCraftingEventMixinIntermediary.firePlayerCraftingItemEvent(this.player, stack)) {
            ci.cancel();
        }
    }

    @Inject(
            method = "checkTakeAchievements(Lnet/minecraft/world/item/ItemStack;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;onCraftedBy(Lnet/minecraft/world/entity/player/Player;I)V"
            )
    )
    public void onCrafted(ItemStack stack, CallbackInfo ci) {
        ConiumCraftingEventMixinIntermediary.firePlayerCraftedItemEvent(this.player, stack);
    }
}
