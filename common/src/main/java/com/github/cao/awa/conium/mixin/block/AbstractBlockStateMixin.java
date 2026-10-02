package com.github.cao.awa.conium.mixin.block;

import com.github.cao.awa.conium.block.event.use.ConiumUseBlockEvent;
import com.github.cao.awa.conium.block.event.used.ConiumUsedBlockEvent;
import com.github.cao.awa.conium.block.event.breaks.ConiumBreakBlockEvent;
import com.github.cao.awa.conium.block.event.breaking.ConiumBreakingBlockEvent;
import com.github.cao.awa.conium.block.event.broken.ConiumBrokenBlockEvent;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import com.github.cao.awa.conium.intermediary.block.ConiumBlockEventMixinIntermediary;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("all")
@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class AbstractBlockStateMixin {
    @Shadow protected abstract BlockState asState();

    @Inject(
            method = "attack",
            at = @At("HEAD"),
            cancellable = true
    )
    public void breakingBlock(Level world, BlockPos blockPos, Player playerEntity, CallbackInfo ci) {
        // Trigger block breaking event.
        if (ConiumBlockEventMixinIntermediary.fireBlockBreakingEvent(
                asState(),
                world,
                playerEntity,
                blockPos
        )) {
            // Cancel this event when intermediary was rejected the event.
            ci.cancel();
        }
    }

    @Redirect(
            method = "useWithoutItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/Block;useWithoutItem(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/InteractionResult;"
            )
    )
    public InteractionResult useBlock(Block instance, BlockState blockState, Level world, BlockPos blockPos, Player playerEntity, BlockHitResult blockHitResult) {
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
