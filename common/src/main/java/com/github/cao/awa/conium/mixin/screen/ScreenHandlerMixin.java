package com.github.cao.awa.conium.mixin.screen;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ScreenHandler.class)
public class ScreenHandlerMixin {
    @Inject(
            method = "handleSlotClick",
            at = @At("HEAD"),
            cancellable = true
    )
    private void handleSlotClick(
            @NotNull PlayerEntity player,
            ClickType clickType,
            Slot slot,
            @NotNull ItemStack stack,
            @NotNull ItemStack cursorStack,
            CallbackInfoReturnable<Boolean> cir
    ) {
        // Request the item stack click context.
        ConiumArisingEventContext<?> clickingContext = ConiumEvent.request(ConiumEventType.ITEM_STACK_CLICK);

        Item item = stack.getItem();

        // Fill the context args.
        clickingContext.put(ConiumEventArgTypes.CLICK_TYPE, clickType)
                .put(ConiumEventArgTypes.PLAYER, player)
                .put(ConiumEventArgTypes.ITEM_STACK, stack)
                .put(ConiumEventArgTypes.CURSOR_STACK, cursorStack)
                .put(ConiumEventArgTypes.SLOT, slot);

        if (clickingContext.presaging(item)) {
            clickingContext.arising(item);
        } else {
            // Cancel this event when presaging was rejected the event.
            cir.setReturnValue(false);
        }
    }
}
