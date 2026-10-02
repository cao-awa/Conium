package com.github.cao.awa.conium.mixin.server.world;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import com.github.cao.awa.conium.intermediary.block.ConiumBlockEventMixinIntermediary;
import com.github.cao.awa.conium.intermediary.entity.ConiumEntityEventMixinIntermediary;
import com.github.cao.awa.conium.intermediary.fluid.ConiumFluidEventMixinIntermediary;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public class ServerWorldMixin {
    private ServerLevel asWorld() {
        return (ServerLevel) (Object) this;
    }

    @Inject(
            method = "tickNonPassenger",
            at = @At(
                    value = "HEAD",
                    target = "Lnet/minecraft/world/entity/Entity;tick()V"
            ),
            cancellable = true
    )
    public void tickEntity(@NotNull Entity entity, CallbackInfo ci) {
        // Trigger the entity tick start event.
        if (ConiumEntityEventMixinIntermediary.fireEntityTickEvent(
                entity
        )) {
            // Cancel entity tick processing.
            ci.cancel();
        }
    }

    @Inject(
            method = "tickNonPassenger",
            at = @At(
                    value = "RETURN",
                    target = "Lnet/minecraft/world/entity/Entity;tick()V"
            )
    )
    public void tickedEntity(@NotNull Entity entity, CallbackInfo ci) {
        // Trigger the entity tick start event.
        ConiumEntityEventMixinIntermediary.fireEntityTickedEvent(
                entity
        );
    }

    @Redirect(
            method = "tickFluid",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/material/FluidState;tick(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V"
            )
    )
    private void scheduledFluidTick(@NotNull FluidState instance, ServerLevel world, BlockPos pos, BlockState blockState) {
        // Trigger the fluid scheduled ticking event.
        if (!ConiumFluidEventMixinIntermediary.fireFluidScheduleTickEvent(
                instance,
                world,
                pos,
                blockState
        )) {
            // Trigger scheduled tick normally.
            instance.tick(world, pos, blockState);
        }
    }

    @Redirect(
            method = "tickBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;tick(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V"
            )
    )
    private void tickBlock(@NotNull BlockState instance, ServerLevel world, BlockPos pos, RandomSource random) {
        // Trigger the block scheduled ticking event.
        if (!ConiumBlockEventMixinIntermediary.fireBlockScheduleTickEvent(
                instance,
                world,
                pos,
                random
        )) {
            // Trigger scheduled tick normally.
            instance.tick(world, pos, random);
        }
    }
}
