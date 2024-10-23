package com.github.cao.awa.conium.mixin.item.stack;

import com.github.cao.awa.conium.Conium;
import com.github.cao.awa.conium.component.ConiumComponentType;
import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.component.*;
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
    public abstract Item getItem();

    @Shadow
    @Final
    ComponentMapImpl components;

    @Unique
    private ItemStack cast() {
        return (ItemStack) (Object) this;
    }

    @Inject(
            method = "<init>(Lnet/minecraft/item/ItemConvertible;ILnet/minecraft/component/ComponentMapImpl;)V",
            at = @At("RETURN")
    )
    public void init(ItemConvertible item, int count, ComponentMapImpl components, CallbackInfo ci) {
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
        ConiumEventContext<?, Boolean> eventContext = ConiumEvent.request(ConiumEventType.ITEM_USE_ON_BLOCK);

        eventContext.put(ConiumEventArgTypes.WORLD, context.getWorld());

        eventContext.put(ConiumEventArgTypes.ITEM_USAGE_CONTEXT, context);

        eventContext.put(ConiumEventArgTypes.PLAYER, context.getPlayer());

        eventContext.put(ConiumEventArgTypes.BLOCK_POS, context.getBlockPos());

        ActionResult result = ActionResult.FAIL;

        if (eventContext.presaging(this)) {
            result = instance.useOnBlock(context);
            if (!eventContext.arising(this) && result != ActionResult.FAIL) {
                result = ActionResult.PASS;
            }
        }

        return result;
    }
}
