package com.github.cao.awa.conium.mixin.block;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.github.cao.awa.conium.block.event.use.ConiumUseBlockEvent;
import com.github.cao.awa.conium.block.event.use.ConiumUsedBlockEvent;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class BlockStateMixin {
    @Inject(
            method = "onBlockBreakStart",
            at = @At("HEAD"),
            cancellable = true
    )
    public void breakingBlock(World world, BlockPos pos, PlayerEntity player, CallbackInfo ci) {
        // Request the breaking block context.
        ConiumEventContext<?> breakingContext = ConiumEvent.request(ConiumEventType.BREAKING_BLOCK);

        AbstractBlock.AbstractBlockState self = cast();

        Block block = self.getBlock();

        // Fill the context args.
        breakingContext.put(ConiumEventArgTypes.WORLD, world);

        breakingContext.put(ConiumEventArgTypes.PLAYER, player);

        breakingContext.put(ConiumEventArgTypes.BLOCK_POS, pos);
        breakingContext.put(ConiumEventArgTypes.BLOCK_STATE, self);

        if (breakingContext.presaging(block)) {
            breakingContext.arising(block);
        } else {
            // Cancel this event when presaging was rejected the event.
            ci.cancel();
        }
    }

    /**
     * Redirect the 'onUse' calling of block using, make event context and trigger the event.
     *
     * @param instance the block instance
     * @param blockState the block state of current context
     * @param world the world of the block
     * @param blockPos the position of the block
     * @param playerEntity the user
     * @param blockHitResult the hit result of the user using to block
     *
     * @see ConiumEventType#USE_BLOCK
     * @see ConiumEventType#USED_BLOCK
     * @see ConiumUseBlockEvent
     * @see ConiumUsedBlockEvent
     * @see Block
     * @see BlockState
     * @see ActionResult
     *
     * @author cao_awa
     *
     * @return the action result
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
        // Create using block context.
        ConiumEventContext<?> usingContext = ConiumEvent.request(ConiumEventType.USE_BLOCK);

        Block block = blockState.getBlock();

        // Fill the context args.
        usingContext.put(ConiumEventArgTypes.WORLD, world)
                .put(ConiumEventArgTypes.PLAYER, playerEntity)
                .put(ConiumEventArgTypes.BLOCK_POS, blockPos)
                .put(ConiumEventArgTypes.BLOCK_STATE, blockState)
                .put(ConiumEventArgTypes.BLOCK_HIT_RESULT, blockHitResult);

        if (usingContext.presaging(block)) {
            usingContext.arising(block);

            // Invoke the 'onUse' method.
            ActionResult result = ((AbstractBlockMixin) instance).invokeOnUse(blockState, world, blockPos, playerEntity, blockHitResult);

            // Create used block context.
            ConiumEventContext<?> usedContext = ConiumEvent.request(ConiumEventType.USED_BLOCK);
            usedContext.inherit(usingContext);

            // Used context has action result to acquire the result, this result not cancel or modifiable.
            usedContext.put(ConiumEventArgTypes.ACTION_RESULT, result);

            if (usedContext.presaging(block)) {
                usedContext.arising(block);
            }

            return result;
        } else {
            // Cancel this event when presaging was rejected the event.
            return ActionResult.FAIL;
        }
    }

    @Unique
    private AbstractBlock.AbstractBlockState cast() {
        return (AbstractBlock.AbstractBlockState) (Object) this;
    }
}
