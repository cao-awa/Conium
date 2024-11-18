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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
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
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;")
    )
    public ActionResult handleUseOnBlock(Item instance, ItemUsageContext context) {
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
}
