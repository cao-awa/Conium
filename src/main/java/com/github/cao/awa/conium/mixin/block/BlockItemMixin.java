package com.github.cao.awa.conium.mixin.block;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class BlockItemMixin {
    @Inject(
            method = "place(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/util/ActionResult;",
            at = @At("HEAD"),
            cancellable = true
    )
    public void placeBlock(ItemPlacementContext context, CallbackInfoReturnable<ActionResult> cir) {
        ConiumEventContext<?> eventContext = ConiumEvent.request(ConiumEventType.PLACE_BLOCK);

        // Fill the context args.
        eventContext.put(ConiumEventArgTypes.ITEM_PLACEMENT_CONTEXT, context);

        if (eventContext.presaging(this)) {
            eventContext.arising(this);
        } else {
            // Cancel this event when presaging was rejected the event.
            cir.setReturnValue(ActionResult.FAIL);
        }
    }

    @Redirect(
            method = "place(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/util/ActionResult;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;onPlaced(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;)V"
            )
    )
    public void placedBlock(Block instance, World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        ConiumEventContext<?> eventContext = ConiumEvent.request(ConiumEventType.PLACED_BLOCK);

        // Fill the context args.
        eventContext.put(ConiumEventArgTypes.WORLD, world);

        eventContext.put(ConiumEventArgTypes.LIVING_ENTITY, placer);

        eventContext.put(ConiumEventArgTypes.BLOCK_POS, pos);
        eventContext.put(ConiumEventArgTypes.BLOCK_STATE, state);

        eventContext.put(ConiumEventArgTypes.ITEM_STACK, itemStack);

        if (eventContext.presaging(this)) {
            // Only presaging state is true can be continues.
            instance.onPlaced(world, pos, state, placer, itemStack);

            eventContext.arising(this);
        }
    }
}
