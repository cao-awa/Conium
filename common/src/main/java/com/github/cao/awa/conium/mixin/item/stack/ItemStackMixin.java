package com.github.cao.awa.conium.mixin.item.stack;

import com.github.cao.awa.conium.Conium;
import com.github.cao.awa.conium.component.ConiumComponentType;
import com.github.cao.awa.conium.config.ConiumConfig;
import com.github.cao.awa.conium.intermediary.mixin.item.ConiumItemEventMixinIntermediary;
import com.github.cao.awa.sinuatum.manipulate.Manipulate;
import net.minecraft.component.ComponentHolder;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.MergedComponentMap;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
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
public abstract class ItemStackMixin implements ComponentHolder {
    @Shadow
    @Final
    MergedComponentMap components;

    @Shadow
    public abstract Item getItem();

    @Unique
    private ItemStack cast() {
        return (ItemStack) (Object) this;
    }

    @Inject(
            method = "<init>(Lnet/minecraft/item/ItemConvertible;ILnet/minecraft/component/MergedComponentMap;)V",
            at = @At("RETURN")
    )
    public void init(ItemConvertible item, int count, MergedComponentMap components, CallbackInfo ci) {
        // Do not apply injection when inject manager isn't prepared.
        if (Conium.itemInjectManager != null) {
            // Inject to current stack.
            Conium.itemInjectManager.inject(cast());
        }
    }

    @Inject(
            method = "getTooltip",
            at = @At(value = "RETURN")
    )
    public void getTooltip(Item.TooltipContext context, @Nullable PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> cir) {
        if (ConiumConfig.debugs) {
            boolean hideTooltip = getOrDefault(DataComponentTypes.TOOLTIP_DISPLAY, TooltipDisplayComponent.DEFAULT).hideTooltip();
            if (type.isCreative() || !hideTooltip) {
                List<Text> tooltip = cir.getReturnValue();

                overallComponents(componentType -> {
                    tooltip.add(Text.translatable(componentType + ": " + this.components.get(componentType)));
                });
            }
        }
    }

    @Unique
    private void overallComponents(Consumer<ConiumComponentType<?>> action) {
        for (ComponentType<?> componentsType : this.components.getTypes()) {
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
    public void onUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        // Trigger item using event.
        if (ConiumItemEventMixinIntermediary.fireItemUseEvent(world, user, hand, user.getStackInHand(hand))) {
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
        ConiumItemEventMixinIntermediary.fireItemUsedEvent(world, user, hand, user.getStackInHand(hand), cir.getReturnValue());
    }

    @Redirect(
            method = "useOnBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/Item;useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;"
            )
    )
    public ActionResult onUseOnBlock(Item instance, ItemUsageContext context) {
        // Trigger item use on block events.
        return ConiumItemEventMixinIntermediary.fireItemUseOnBlock(context);
    }

    @Redirect(
            method = "useOnEntity",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/Item;useOnEntity(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/ActionResult;"
            )
    )
    public ActionResult preUseOnEntity(Item instance, ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        // Trigger item use on entity events.
        return ConiumItemEventMixinIntermediary.fireItemUseOnEntity(stack, user, entity, hand);
    }

    @Inject(
            method = "usageTick",
            at = @At("HEAD"),
            cancellable = true
    )
    public void preUsageTick(World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
        // Trigger item pre-usage tick event.
        if (ConiumItemEventMixinIntermediary.fireItemPreUsageTickEvent(world, user, Manipulate.cast(this), remainingUseTicks)) {
            // Cancel this event when intermediary was rejected the event.
            ci.cancel();
        }
    }

    @Inject(
            method = "usageTick",
            at = @At("RETURN")
    )
    public void onUsageTick(World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
        // Trigger item usage tick event.
        ConiumItemEventMixinIntermediary.fireItemUsageTickEvent(world, user, Manipulate.cast(this), remainingUseTicks);
    }

    @Inject(
            method = "onStackClicked",
            at = @At("RETURN")
    )
    private void handleSlotClicked(Slot slot, ClickType clickType, PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        // Trigger item stack clicked event.
        ConiumItemEventMixinIntermediary.fireItemStackClickedEvent(player, Manipulate.cast(this), slot, clickType);
    }

    @Inject(
            method = "inventoryTick",
            at = @At("HEAD"),
            cancellable = true
    )
    public void preInventoryTick(World world, Entity entity, EquipmentSlot slot, CallbackInfo ci) {
        // Trigger item inventory tick event.
        if (ConiumItemEventMixinIntermediary.fireItemInventoryTickEvent(world, entity, Manipulate.cast(this), slot)) {
            // Cancel this event when intermediary was rejected the event.
            ci.cancel();
        }
    }

    @Inject(
            method = "inventoryTick",
            at = @At("RETURN")
    )
    public void handleInventoryTick(World world, Entity entity, EquipmentSlot slot, CallbackInfo ci) {
        // Trigger item inventory ticked event.
        ConiumItemEventMixinIntermediary.fireItemInventoryTickedEvent(world, entity, Manipulate.cast(this), slot);
    }
}
