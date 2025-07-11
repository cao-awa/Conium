package com.github.cao.awa.conium.mixin.server.interaction;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OperatorBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerInteractionManager.class)
public abstract class ServerPlayerInteractionManagerMixin {
    @Shadow
    protected ServerWorld world;

    @Shadow
    @Final
    protected ServerPlayerEntity player;
    @Shadow
    private GameMode gameMode;

    @Shadow
    public abstract boolean isCreative();

    @Inject(
            method = "tryBreakBlock",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    public void tryBreakBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockState blockState = this.world.getBlockState(pos);
        ItemStack stack = this.player.getMainHandStack();
        if (!stack.getItem().canMine(stack, blockState, this.world, pos, this.player)) {
            cir.setReturnValue(false);
        } else {
            Block block = blockState.getBlock();
            if (block instanceof OperatorBlock && !this.player.isCreativeLevelTwoOp()) {
                this.world.updateListeners(pos, blockState, blockState, Block.NOTIFY_ALL);
                cir.setReturnValue(false);
            } else if (this.player.isBlockBreakingRestricted(this.world, pos, this.gameMode)) {
                cir.setReturnValue(false);
            } else {
                // Request the block break event.
                ConiumArisingEventContext<?> breakContext = ConiumEvent.request(ConiumEventType.BREAK_BLOCK);

                // Fill the context args.
                breakContext.put(ConiumEventArgTypes.WORLD, world)
                        .put(ConiumEventArgTypes.PLAYER, player)
                        .put(ConiumEventArgTypes.BLOCK_POS, pos)
                        .put(ConiumEventArgTypes.BLOCK_STATE, blockState);

                // Arising the block break context.
                if (breakContext.presaging(block)) {
                    breakContext.arising(block);
                } else {
                    // Cancel this event when presaging was rejected the event.
                    cir.setReturnValue(false);
                    return;
                }

                BlockState brokenState = block.onBreak(this.world, pos, blockState, this.player);
                boolean removedBlock = this.world.removeBlock(pos, false);
                if (removedBlock) {
                    // Request the block broken event.
                    ConiumArisingEventContext<?> brokenContext = ConiumEvent.request(ConiumEventType.BROKEN_BLOCK);

                    // Fill the context args.
                    brokenContext.inherit(breakContext);
                    brokenContext.put(ConiumEventArgTypes.BLOCK_STATE, brokenState);

                    // The block broken event is not cancelable, only arising the context.
                    if (brokenContext.presaging(block)) {
                        brokenContext.arising(block);
                    }

                    block.onBroken(this.world, pos, brokenState);
                }

                if (isCreative()) {
                    cir.setReturnValue(true);
                } else {
                    boolean canHarvest = this.player.canHarvest(brokenState);
                    stack.postMine(this.world, brokenState, pos, this.player);
                    if (removedBlock && canHarvest) {
                        block.afterBreak(
                                this.world,
                                this.player,
                                pos,
                                brokenState,
                                this.world.getBlockEntity(pos),
                                stack.copy()
                        );
                    }

                    cir.setReturnValue(true);
                }
            }
        }
    }
}
