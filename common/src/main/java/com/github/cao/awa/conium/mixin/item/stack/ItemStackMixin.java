package com.github.cao.awa.conium.mixin.item.stack;

import com.github.cao.awa.conium.Conium;
import com.github.cao.awa.conium.component.ConiumComponentType;
import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.component.ComponentHolder;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.MergedComponentMap;
import net.minecraft.entity.Entity;
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
        // Do not apply inject when inject manager aren't prepared.
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
        if (Conium.enableDebugs) {
            if (type.isCreative() || !contains(DataComponentTypes.HIDE_TOOLTIP)) {
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
            if (componentsType instanceof ConiumComponentType<?> trtrComponentType) {
                action.accept(trtrComponentType);
            }
        }
    }

    @Redirect(
            method = "useOnBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/Item;useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;"
            )
    )
    public ActionResult onUseOnBlock(Item instance, ItemUsageContext context) {
        // Request the item using on block context.
        ConiumEventContext<?> usingContext = ConiumEvent.request(ConiumEventType.ITEM_USE_ON_BLOCK);

        // Fill the context args.
        usingContext.put(ConiumEventArgTypes.WORLD, context.getWorld())
                .put(ConiumEventArgTypes.ITEM_USAGE_CONTEXT, context)
                .put(ConiumEventArgTypes.PLAYER, context.getPlayer())
                .put(ConiumEventArgTypes.BLOCK_POS, context.getBlockPos());

        if (usingContext.presaging(instance)) {
            usingContext.arising(instance);

            // Invoke 'useOnBlock' method.
            ActionResult result = instance.useOnBlock(context);

            // Request the item used on block context.
            ConiumEventContext<?> usedContext = ConiumEvent.request(ConiumEventType.ITEM_USED_ON_BLOCK);
            usedContext.inherit(usingContext);

            // Used context has an action result to acquire the result, this result not cancel or modifiable.
            usedContext.put(ConiumEventArgTypes.ACTION_RESULT, result);

            if (usedContext.presaging(instance)) {
                usedContext.arising(instance);
            }

            return result;
        } else {
            System.out.println("Canceled #onUseOnBlock");

            // Cancel this event when presaging was rejected the event.
            return ActionResult.FAIL;
        }
    }

    @Redirect(
            method = "useOnEntity",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/Item;useOnEntity(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/ActionResult;"
            )
    )
    public ActionResult preUseOnEntity(Item instance, ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        // Request the item using on entity context.
        ConiumEventContext<?> usingContext = ConiumEvent.request(ConiumEventType.ITEM_USE_ON_ENTITY);

        // Fill the context args.
        usingContext.put(ConiumEventArgTypes.PLAYER, user)
                .put(ConiumEventArgTypes.LIVING_ENTITY, entity)
                .put(ConiumEventArgTypes.ITEM_STACK, stack)
                .put(ConiumEventArgTypes.HAND, hand);

        if (usingContext.presaging(instance)) {
            usingContext.arising(instance);

            // Invoke 'useOnEntity' method.
            ActionResult result = instance.useOnEntity(stack, user, entity, hand);

            // Request the item used on entity context.
            ConiumEventContext<?> usedContext = ConiumEvent.request(ConiumEventType.ITEM_USED_ON_ENTITY);
            usedContext.inherit(usingContext);

            // Used context has action result to acquire the result, this result not cancel or modifiable.
            usedContext.put(ConiumEventArgTypes.ACTION_RESULT, result);

            if (usedContext.presaging(instance)) {
                usedContext.arising(instance);
            }

            return result;
        } else {
            // Cancel this event when presaging was rejected the event.
            return ActionResult.FAIL;
        }
    }

    @Inject(
            method = "usageTick",
            at = @At("HEAD"),
            cancellable = true
    )
    public void preUsageTick(World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
        // Request the item usage tick context.
        ConiumEventContext<?> usingContext = ConiumEvent.request(ConiumEventType.ITEM_USAGE_TICK);

        ItemStack stack = cast();
        Item item = getItem();

        // Fill the context args.
        usingContext.put(ConiumEventArgTypes.WORLD, world)
                .put(ConiumEventArgTypes.LIVING_ENTITY, user)
                .put(ConiumEventArgTypes.ITEM_STACK, stack)
                .put(ConiumEventArgTypes.REMAINING_USE_TICKS, remainingUseTicks);

        if (usingContext.presaging(item)) {
            usingContext.arising(item);
        } else {
            // Cancel this event when presaging was rejected the event.
            ci.cancel();
        }
    }

    @Inject(
            method = "usageTick",
            at = @At("RETURN")
    )
    public void onUsageTick(World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
        // Request the item usage ticked context.
        ConiumEventContext<?> usingContext = ConiumEvent.request(ConiumEventType.ITEM_USAGE_TICKED);

        ItemStack stack = cast();
        Item item = getItem();

        // Fill the context args.
        usingContext.put(ConiumEventArgTypes.WORLD, world)
                .put(ConiumEventArgTypes.LIVING_ENTITY, user)
                .put(ConiumEventArgTypes.ITEM_STACK, stack)
                .put(ConiumEventArgTypes.REMAINING_USE_TICKS, remainingUseTicks);

        // Usage ticked event cannot cancel because it already completed.
        if (usingContext.presaging(item)) {
            usingContext.arising(item);
        }
    }

    @Inject(
            method = "onStackClicked",
            at = @At("RETURN")
    )
    private void handleSlotClicked(Slot slot, ClickType clickType, PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        // Request the item stack click context.
        ConiumEventContext<?> clickedContext = ConiumEvent.request(ConiumEventType.ITEM_STACK_CLICKED);

        Item item = getItem();

        // Fill the context args.
        clickedContext.put(ConiumEventArgTypes.CLICK_TYPE, clickType)
                .put(ConiumEventArgTypes.PLAYER, player)
                .put(ConiumEventArgTypes.ITEM_STACK, cast())
                .put(ConiumEventArgTypes.SLOT, slot);

        // Item stack clicked event cannot cancel because it already completed.
        if (clickedContext.presaging(item)) {
            clickedContext.arising(item);
        }
    }

    @Inject(
            method = "inventoryTick",
            at = @At("HEAD"),
            cancellable = true
    )
    public void preInventoryTick(World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        // Request the item stack inventory tick context.
        ConiumEventContext<?> tickingContext = ConiumEvent.request(ConiumEventType.ITEM_INVENTORY_TICK);

        Item item = getItem();

        // Fill the context args.
        tickingContext.put(ConiumEventArgTypes.WORLD, world)
                .put(ConiumEventArgTypes.ENTITY, entity)
                .put(ConiumEventArgTypes.ITEM_STACK, cast())
                .put(ConiumEventArgTypes.SLOT_NUMBER, slot)
                .put(ConiumEventArgTypes.SELECT_STATUS, selected);

        if (tickingContext.presaging(item)) {
            tickingContext.arising(item);
        } else {
            // Cancel this event when presaging was rejected the event.
            ci.cancel();
        }
    }

    @Inject(
            method = "inventoryTick",
            at = @At("RETURN")
    )
    public void handleInventoryTick(World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        // Request the item stack inventory ticked context.
        ConiumEventContext<?> tickedContext = ConiumEvent.request(ConiumEventType.ITEM_INVENTORY_TICKED);

        Item item = getItem();

        // Fill the context args.
        tickedContext.put(ConiumEventArgTypes.WORLD, world)
                .put(ConiumEventArgTypes.ENTITY, entity)
                .put(ConiumEventArgTypes.ITEM_STACK, cast())
                .put(ConiumEventArgTypes.SLOT_NUMBER, slot)
                .put(ConiumEventArgTypes.SELECT_STATUS, selected);

        // Item stack inventory ticked event cannot cancel because it already completed.
        if (tickedContext.presaging(item)) {
            tickedContext.arising(item);
        }
    }
}
