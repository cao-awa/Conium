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
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class BlockStateMixin {
    @Shadow protected abstract BlockState asBlockState();

    @Inject(
            method = "onBlockBreakStart",
            at = @At("HEAD"),
            cancellable = true
    )
    public void breakBlock(World world, BlockPos pos, PlayerEntity player, CallbackInfo ci) {
        ConiumEventContext<?> eventContext = ConiumEvent.request(ConiumEventType.BREAK_BLOCK);

        AbstractBlock.AbstractBlockState self = cast();

        // Fill the context args.
        eventContext.put(ConiumEventArgTypes.WORLD, world);

        eventContext.put(ConiumEventArgTypes.PLAYER, player);

        eventContext.put(ConiumEventArgTypes.BLOCK_POS, pos);
        eventContext.put(ConiumEventArgTypes.BLOCK_STATE, self);

        if (eventContext.presaging(self)) {
            eventContext.arising(self);
        } else {
            // Cancel this event when presaging was rejected the event.
            ci.cancel();
        }
    }

    @Redirect(
            method = "onUse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;onUse(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/hit/BlockHitResult;)Lnet/minecraft/util/ActionResult;"
            )
    )
    public ActionResult useBlock(Block instance, BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, BlockHitResult blockHitResult) {
        ConiumEventContext<?> usingContext = ConiumEvent.request(ConiumEventType.USE_BLOCK);
        ConiumEventContext<?> usedContext = ConiumEvent.request(ConiumEventType.USED_BLOCK);

        // Fill the context args.
        usingContext.put(ConiumEventArgTypes.WORLD, world);

        usingContext.put(ConiumEventArgTypes.PLAYER, playerEntity);

        usingContext.put(ConiumEventArgTypes.BLOCK_POS, blockPos);
        usingContext.put(ConiumEventArgTypes.BLOCK_STATE, blockState);
        usingContext.put(ConiumEventArgTypes.BLOCK_HIT_RESULT, blockHitResult);

        usedContext.inherit(usingContext);

        if (usingContext.presaging(blockState)) {
            usingContext.arising(blockState);

            ActionResult result = ((BlockMixin) instance).invokeOnUse(blockState, world, blockPos, playerEntity, blockHitResult);

            usedContext.put(ConiumEventArgTypes.ACTION_RESULT, result);

            if (usedContext.presaging(blockState)) {
                usedContext.arising(blockState);
            }

            return result;
        } else {
            // Cancel this event when presaging was rejected the event.
            return ActionResult.FAIL;
        }
    }

    private AbstractBlock.AbstractBlockState cast() {
        return (AbstractBlock.AbstractBlockState) (Object) this;
    }
}
