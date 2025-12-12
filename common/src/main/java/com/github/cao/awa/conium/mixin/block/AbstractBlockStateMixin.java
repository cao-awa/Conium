package com.github.cao.awa.conium.mixin.block;

import com.github.cao.awa.conium.block.event.use.ConiumUseBlockEvent;
import com.github.cao.awa.conium.block.event.used.ConiumUsedBlockEvent;
import com.github.cao.awa.conium.block.event.breaks.ConiumBreakBlockEvent;
import com.github.cao.awa.conium.block.event.breaking.ConiumBreakingBlockEvent;
import com.github.cao.awa.conium.block.event.broken.ConiumBrokenBlockEvent;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import com.github.cao.awa.conium.intermediary.block.ConiumBlockEventMixinIntermediary;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("all")
@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin {
    @Shadow protected abstract BlockState asBlockState();

    /**
     * Trigger block breaking event when the block starts breaking.
     *
     * @param world        the world of the block
     * @param blockPos     the position of the block
     * @param playerEntity the miner
     * @param ci           the callback info
     *
     * @see Block#onBlockBreakStart(BlockState, World, BlockPos, PlayerEntity)
     * @see ConiumEventType#BREAK_BLOCK
     * @see ConiumEventType#BREAKING_BLOCK
     * @see ConiumEventType#BROKEN_BLOCK
     * @see ConiumBreakBlockEvent
     * @see ConiumBreakingBlockEvent
     * @see ConiumBrokenBlockEvent
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @Inject(
            method = "onBlockBreakStart",
            at = @At("HEAD"),
            cancellable = true
    )
    public void breakingBlock(World world, BlockPos blockPos, PlayerEntity playerEntity, CallbackInfo ci) {
        // Trigger block breaking event.
        if (ConiumBlockEventMixinIntermediary.fireBlockBreakingEvent(
                asBlockState(),
                world,
                playerEntity,
                blockPos
        )) {
            // Cancel this event when intermediary was rejected the event.
            ci.cancel();
        }
    }

    /**
     * Redirect the 'onUse' calling of block using, make event context and trigger the event.
     *
     * @param instance       the block instance
     * @param blockState     the block state of current context
     * @param world          the world of the block
     * @param blockPos       the position of the block
     * @param playerEntity   the user
     * @param blockHitResult the hit result of the user using to block
     *
     * @return the action result
     *
     * @see Block
     * @see BlockState
     * @see ConiumEventType#USE_BLOCK
     * @see ConiumEventType#USED_BLOCK
     * @see ConiumUseBlockEvent
     * @see ConiumUsedBlockEvent
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @Redirect(
            method = "onUse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;onUse(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/hit/BlockHitResult;)Lnet/minecraft/util/ActionResult;"
            )
    )
    public ActionResult useBlock(Block instance, BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, BlockHitResult blockHitResult) {
        // Trigger block usage event.
        return ConiumBlockEventMixinIntermediary.fireBlockUsageEvent(
                blockState,
                world,
                playerEntity,
                blockPos,
                blockHitResult
        );
    }
}
