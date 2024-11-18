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
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public abstract class BlockItemMixin {
    @Shadow public abstract Block getBlock();

    /**
     * Inject to {@code place} to trigger event {@code PLACE_BLOCK}.
     *
     * @param context the placement context
     * @author cao_awa
     * @see ConiumEventType#PLACE_BLOCK
     * @see com.github.cao.awa.conium.block.event.place.ConiumPlaceBlockEvent ConiumPlaceBlockEvent
     * @see com.github.cao.awa.conium.block.event.place.ConiumPlacedBlockEvent ConiumPlacedBlockEvent
     * @see BlockItemMixin#placedBlock
     * @since 1.0.0
     */
    @Inject(
            method = "place(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/util/ActionResult;",
            at = @At("HEAD"),
            cancellable = true
    )
    public void placeBlock(ItemPlacementContext context, CallbackInfoReturnable<ActionResult> cir) {
        // Request the event.
        ConiumEventContext<?> placeBlockContext = ConiumEvent.request(ConiumEventType.PLACE_BLOCK);

        Block block = getBlock();

        // Fill the context args.
        placeBlockContext.put(ConiumEventArgTypes.ITEM_PLACEMENT_CONTEXT, context);

        if (placeBlockContext.presaging(block)) {
            // Only presaging state is true can be continues.
            placeBlockContext.arising(block);
        } else {
            // Cancel this event when presaging was rejected the event.
            cir.setReturnValue(ActionResult.FAIL);
        }
    }

    /**
     * Redirect the {@code onPlaced} to trigger event {@code PLACED_BLOCK}.
     *
     * @param instance  the block
     * @param world     the world
     * @param pos       the position of placed block
     * @param state     the state of placed block
     * @param placer    the placer
     * @param itemStack the item stack that used to place the block
     * @author cao_awa
     * @see ConiumEventType#PLACED_BLOCK
     * @see com.github.cao.awa.conium.block.event.place.ConiumPlacedBlockEvent ConiumPlacedBlockEvent
     * @see com.github.cao.awa.conium.block.event.place.ConiumPlaceBlockEvent ConiumPlaceBlockEvent
     * @see BlockItemMixin#placeBlock
     * @since 1.0.0
     */
    @Redirect(
            method = "place(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/util/ActionResult;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;onPlaced(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;)V"
            )
    )
    public void placedBlock(Block instance, World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        // Request the event.
        ConiumEventContext<?> placedBlockContext = ConiumEvent.request(ConiumEventType.PLACED_BLOCK);

        Block block = getBlock();

        // Fill the context args.
        placedBlockContext.put(ConiumEventArgTypes.WORLD, world);

        placedBlockContext.put(ConiumEventArgTypes.LIVING_ENTITY, placer);

        placedBlockContext.put(ConiumEventArgTypes.BLOCK_POS, pos);
        placedBlockContext.put(ConiumEventArgTypes.BLOCK_STATE, state);

        placedBlockContext.put(ConiumEventArgTypes.ITEM_STACK, itemStack);

        if (placedBlockContext.presaging(block)) {
            // Only presaging state is true can be continues.
            instance.onPlaced(world, pos, state, placer, itemStack);

            placedBlockContext.arising(block);
        }
    }
}
