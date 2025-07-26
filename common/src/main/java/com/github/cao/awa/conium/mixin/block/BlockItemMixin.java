package com.github.cao.awa.conium.mixin.block;

import com.github.cao.awa.conium.block.event.place.ConiumPlaceBlockEvent;
import com.github.cao.awa.conium.block.event.place.ConiumPlacedBlockEvent;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import com.github.cao.awa.conium.intermediary.block.ConiumBlockEventMixinIntermediary;
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
    @Shadow
    public abstract Block getBlock();

    /**
     * Inject to {@code place} calling to trigger event {@code PLACE_BLOCK}.
     *
     * @param placementContext the placement context
     * @param cir              the callback info
     *
     * @see ConiumEventType#PLACE_BLOCK
     * @see ConiumPlaceBlockEvent
     * @see BlockItemMixin#placedBlock
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @Inject(
            method = "place(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/util/ActionResult;",
            at = @At("HEAD"),
            cancellable = true
    )
    public void placeBlock(ItemPlacementContext placementContext, CallbackInfoReturnable<ActionResult> cir) {
        // Trigger block placing event.
        if (ConiumBlockEventMixinIntermediary.firePlaceBlockEvent(
                getBlock(),
                placementContext
        )) {
            // Cancel this event when intermediary was rejected the event.
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
     *
     * @see ConiumEventType#PLACED_BLOCK
     * @see ConiumPlacedBlockEvent
     * @see BlockItemMixin#placeBlock
     *
     * @author cao_awa
     *
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
        // Trigger block placing event.
        ConiumBlockEventMixinIntermediary.firePlacedBlockEvent(
                state,
                world,
                placer,
                pos,
                itemStack
        );
    }
}
