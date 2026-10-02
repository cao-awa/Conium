package com.github.cao.awa.conium.mixin.server.interaction;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.GameMasterBlock;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.GameType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerGameMode.class)
public abstract class ServerPlayerInteractionManagerMixin {
    @Shadow
    protected ServerLevel level;

    @Shadow
    @Final
    protected ServerPlayer player;
    @Shadow
    private GameType gameModeForPlayer;

    @Shadow
    public abstract boolean isCreative();

    @Inject(
            method = "destroyBlock",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    public void tryBreakBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockState blockState = this.level.getBlockState(pos);
        ItemStack stack = this.player.getMainHandItem();
        if (!stack.canDestroyBlock(blockState, this.level, pos, this.player)) {
            cir.setReturnValue(false);
        } else {
            Block block = blockState.getBlock();
            if (block instanceof GameMasterBlock && !this.player.canUseGameMasterBlocks()) {
                this.level.sendBlockUpdated(pos, blockState, blockState, 3);
                cir.setReturnValue(false);
            } else if (this.player.blockActionRestricted(this.level, pos, this.gameModeForPlayer)) {
                cir.setReturnValue(false);
            } else {
                // Request the block break event.
                ConiumArisingEventContext<?, ?> breakContext = ConiumEvent.request(ConiumEventType.BREAK_BLOCK);

                // Fill the context args.
                breakContext.put(ConiumEventArgTypes.WORLD, this.level)
                        .put(ConiumEventArgTypes.PLAYER, this.player)
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

                BlockState brokenState = block.playerWillDestroy(this.level, pos, blockState, this.player);
                boolean removedBlock = this.level.removeBlock(pos, false);
                if (removedBlock) {
                    // Request the block broken event.
                    ConiumArisingEventContext<?, ?> brokenContext = ConiumEvent.request(ConiumEventType.BROKEN_BLOCK);

                    // Fill the context args.
                    brokenContext.inherit(breakContext);
                    brokenContext.put(ConiumEventArgTypes.BLOCK_STATE, brokenState);

                    // The block broken event is not cancelable, only arising the context.
                    if (brokenContext.presaging(block)) {
                        brokenContext.arising(block);
                    }

                    block.destroy(this.level, pos, brokenState);
                }

                if (this.player.preventsBlockDrops()) {
                    cir.setReturnValue(true);
                } else {
                    boolean canHarvest = this.player.hasCorrectToolForDrops(brokenState);
                    stack.mineBlock(this.level, brokenState, pos, this.player);
                    if (removedBlock && canHarvest) {
                        block.playerDestroy(
                                this.level,
                                this.player,
                                pos,
                                brokenState,
                                this.level.getBlockEntity(pos),
                                stack.copy()
                        );
                    }

                    cir.setReturnValue(true);
                }
            }
        }
    }
}
