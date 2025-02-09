package com.github.cao.awa.conium.mixin.item;

import com.github.cao.awa.conium.intermediary.mixin.item.ConiumItemEventMixinIntermediary;
import com.github.cao.awa.sinuatum.manipulate.Manipulate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(
            method = "use",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        // Trigger item using event.
        if (ConiumItemEventMixinIntermediary.fireItemUseEvent(world, user, hand, Manipulate.cast(this))) {
            // Cancel this event when intermediary was rejected the event.
            cir.setReturnValue(ActionResult.FAIL);
        }
    }

    @Inject(
            method = "use",
            at = @At("RETURN")
    )
    public void onUsed(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        // Trigger item used event.
        ConiumItemEventMixinIntermediary.fireItemUsedEvent(world, user, hand, Manipulate.cast(this), cir.getReturnValue());
    }
}
