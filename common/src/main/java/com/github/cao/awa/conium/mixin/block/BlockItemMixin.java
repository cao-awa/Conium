package com.github.cao.awa.conium.mixin.block;

import com.github.cao.awa.conium.block.event.place.ConiumPlaceBlockEvent;
import com.github.cao.awa.conium.block.event.placed.ConiumPlacedBlockEvent;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import com.github.cao.awa.conium.intermediary.block.ConiumBlockEventMixinIntermediary;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
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

    @Inject(
            method = "place(Lnet/minecraft/world/item/context/BlockPlaceContext;)Lnet/minecraft/world/InteractionResult;",
            at = @At("HEAD"),
            cancellable = true
    )
    public void placeBlock(BlockPlaceContext placementContext, CallbackInfoReturnable<InteractionResult> cir) {
        // Trigger block placing event.
        if (ConiumBlockEventMixinIntermediary.firePlaceBlockEvent(
                getBlock(),
                placementContext
        )) {
            // Cancel this event when intermediary was rejected the event.
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }

    @Redirect(
            method = "place(Lnet/minecraft/world/item/context/BlockPlaceContext;)Lnet/minecraft/world/InteractionResult;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/Block;setPlacedBy(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;)V"
            )
    )
    public void placedBlock(Block instance, Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
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
