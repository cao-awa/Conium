package com.github.cao.awa.conium.mixin.item.stack;

import com.github.cao.awa.conium.Conium;
import com.github.cao.awa.conium.component.ConiumComponentType;
import com.github.cao.awa.conium.config.ConiumConfig;
import com.github.cao.awa.conium.intermediary.item.ConiumItemEventMixinIntermediary;
import com.github.cao.awa.translator.structuring.cast.Caster;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.inventory.Slot;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements DataComponentHolder {
    @Shadow
    @Final
    private PatchedDataComponentMap components;

    @Shadow
    public abstract Item getItem();

    @Unique
    private ItemStack cast() {
        return (ItemStack) (Object) this;
    }

    @Inject(
            method = "<init>(Lnet/minecraft/core/Holder;ILnet/minecraft/core/component/PatchedDataComponentMap;)V",
            at = @At("RETURN")
    )
    public void init(Holder<Item> item, int count, PatchedDataComponentMap components, CallbackInfo ci) {
        // Do not apply injection when inject manager isn't prepared.
        if (Conium.itemInjectManager != null) {
            // Inject to current stack.
            Conium.itemInjectManager.inject(cast());
        }
    }

    @Inject(
            method = "getTooltipLines",
            at = @At(value = "RETURN")
    )
    public void getTooltip(Item.TooltipContext context, @Nullable Player player, TooltipFlag type, CallbackInfoReturnable<List<Component>> cir) {
        if (ConiumConfig.debugs) {
            boolean hideTooltip = getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT).hideTooltip();
            if (type.isCreative() || !hideTooltip) {
                List<Component> tooltip = cir.getReturnValue();

                overallComponents(componentType -> {
                    tooltip.add(Component.translatable(componentType + ": " + this.components.get(componentType)));
                });
            }
        }
    }

    @Unique
    private void overallComponents(Consumer<ConiumComponentType<?>> action) {
        for (DataComponentType<?> componentsType : this.components.keySet()) {
            if (componentsType instanceof ConiumComponentType<?> coniumComponentType) {
                action.accept(coniumComponentType);
            }
        }
    }

    @Inject(
            method = "use",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onUse(Level world, Player user, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        // Trigger item using event.
        if (ConiumItemEventMixinIntermediary.fireItemUseEvent(world, user, hand, user.getItemInHand(hand))) {
            // Cancel this event when intermediary was rejected the event.
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }

    @Inject(
            method = "use",
            at = @At("RETURN")
    )
    public void onUsed(Level world, Player user, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        // Trigger item used event.
        ConiumItemEventMixinIntermediary.fireItemUsedEvent(world, user, hand, user.getItemInHand(hand), cir.getReturnValue());
    }

    @Redirect(
            method = "useOn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/Item;useOn(Lnet/minecraft/world/item/context/UseOnContext;)Lnet/minecraft/world/InteractionResult;"
            )
    )
    public InteractionResult onUseOnBlock(Item instance, UseOnContext context) {
        // Trigger item use on block events.
        return ConiumItemEventMixinIntermediary.fireItemUseOnBlock(context);
    }

    @Redirect(
            method = "interactLivingEntity",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/Item;interactLivingEntity(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;"
            )
    )
    public InteractionResult preUseOnEntity(Item instance, ItemStack stack, Player user, LivingEntity entity, InteractionHand hand) {
        // Trigger item use on entity events.
        return ConiumItemEventMixinIntermediary.fireItemUseOnEntity(stack, user, entity, hand);
    }

    @Inject(
            method = "onUseTick",
            at = @At("HEAD"),
            cancellable = true
    )
    public void preUsageTick(Level world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
        // Trigger item pre-usage tick event.
        if (ConiumItemEventMixinIntermediary.fireItemPreUsageTickEvent(world, user, Caster.cast(this), remainingUseTicks)) {
            // Cancel this event when intermediary was rejected the event.
            ci.cancel();
        }
    }

    @Inject(
            method = "onUseTick",
            at = @At("RETURN")
    )
    public void onUsageTick(Level world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
        // Trigger item usage tick event.
        ConiumItemEventMixinIntermediary.fireItemUsageTickEvent(world, user, Caster.cast(this), remainingUseTicks);
    }

    @Inject(
            method = "overrideStackedOnOther",
            at = @At("RETURN")
    )
    private void handleSlotClicked(Slot slot, ClickAction clickType, Player player, CallbackInfoReturnable<Boolean> cir) {
        // Trigger item stack clicked event.
        ConiumItemEventMixinIntermediary.fireItemStackClickedEvent(player, Caster.cast(this), slot, clickType);
    }

    @Inject(
            method = "inventoryTick",
            at = @At("HEAD"),
            cancellable = true
    )
    public void preInventoryTick(Level world, Entity entity, EquipmentSlot slot, CallbackInfo ci) {
        // Trigger item inventory tick event.
        if (ConiumItemEventMixinIntermediary.fireItemInventoryTickEvent(world, entity, Caster.cast(this), slot)) {
            // Cancel this event when intermediary was rejected the event.
            ci.cancel();
        }
    }

    @Inject(
            method = "inventoryTick",
            at = @At("RETURN")
    )
    public void handleInventoryTick(Level world, Entity entity, EquipmentSlot slot, CallbackInfo ci) {
        // Trigger item inventory ticked event.
        ConiumItemEventMixinIntermediary.fireItemInventoryTickedEvent(world, entity, Caster.cast(this), slot);
    }
}
