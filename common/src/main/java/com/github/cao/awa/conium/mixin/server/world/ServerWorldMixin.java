package com.github.cao.awa.conium.mixin.server.world;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import com.github.cao.awa.conium.intermediary.block.ConiumBlockEventMixinIntermediary;
import com.github.cao.awa.conium.intermediary.entity.ConiumEntityEventMixinIntermediary;
import com.github.cao.awa.conium.intermediary.fluid.ConiumFluidEventMixinIntermediary;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    private ServerWorld asWorld() {
        return (ServerWorld) (Object) this;
    }

    @Inject(
            method = "tickEntity",
            at = @At(
                    value = "HEAD",
                    target = "Lnet/minecraft/entity/Entity;tick()V"
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
            method = "tickEntity",
            at = @At(
                    value = "RETURN",
                    target = "Lnet/minecraft/entity/Entity;tick()V"
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
                    target = "Lnet/minecraft/fluid/FluidState;onScheduledTick(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V"
            )
    )
    private void scheduledFluidTick(@NotNull FluidState instance, ServerWorld world, BlockPos pos, BlockState blockState) {
        // Trigger the fluid scheduled ticking event.
        if (!ConiumFluidEventMixinIntermediary.fireFluidScheduleTickEvent(
                instance,
                world,
                pos,
                blockState
        )) {
            // Trigger scheduled tick normally.
            instance.onScheduledTick(world, pos, blockState);
        }
    }

    @Redirect(
            method = "tickBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/BlockState;scheduledTick(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/random/Random;)V"
            )
    )
    private void tickBlock(@NotNull BlockState instance, ServerWorld world, BlockPos pos, Random random) {
        // Trigger the block scheduled ticking event.
        if (!ConiumBlockEventMixinIntermediary.fireBlockScheduleTickEvent(
                instance,
                world,
                pos,
                random
        )) {
            // Trigger scheduled tick normally.
            instance.scheduledTick(world, pos, random);
        }
    }
}
