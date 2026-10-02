package com.github.cao.awa.conium.mixin.client.interaction;

import com.github.cao.awa.conium.intermediary.block.ConiumBlockEventMixinIntermediary;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MultiPlayerGameMode.class)
public class ClientPlayerInteractionManagerMixin {
    @Redirect(
            method = "destroyBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/Block;destroy(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V"
            )
    )
    public void breakBlock(Block instance, LevelAccessor world, BlockPos pos, BlockState state) {
        // Trigger block broken event.
        ConiumBlockEventMixinIntermediary.fireBlockBrokenEvent(
                instance,
                (Level) world,
                pos,
                state
        );
    }
}
