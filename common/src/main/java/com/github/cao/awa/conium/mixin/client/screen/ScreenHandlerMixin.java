package com.github.cao.awa.conium.mixin.client.screen;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.ClickAction;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractContainerMenu.class)
public class ScreenHandlerMixin {
    @Inject(
            method = "tryItemClickBehaviourOverride",
            at = @At("HEAD"),
            cancellable = true
    )
    private void handleSlotClick(
            @NotNull Player player,
            ClickAction clickType,
            Slot slot,
            @NotNull ItemStack stack,
            @NotNull ItemStack cursorStack,
            CallbackInfoReturnable<Boolean> cir
    ) {
        // Request the item stack click context.
        ConiumArisingEventContext<?, ?> clickingContext = ConiumEvent.request(ConiumEventType.ITEM_STACK_CLICK);

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
