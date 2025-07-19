package com.github.cao.awa.conium.mixin.server.world;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
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
        // Request the entity ticking context.
        ConiumArisingEventContext<?, ?> tickingContext = ConiumEvent.request(ConiumEventType.ENTITY_TICK);

        EntityType<?> type = entity.getType();

        // Fill the context args.
        tickingContext.put(ConiumEventArgTypes.WORLD, asWorld())
                .put(ConiumEventArgTypes.ENTITY, entity);

        if (tickingContext.presaging(type)) {
            // Only presaging state is true can be continued.
            tickingContext.arising(type);
        } else {
            // Cancel this event when presaging was rejected the event.
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
        // Request the entity ticked context.
        ConiumArisingEventContext<?, ?> tickedContext = ConiumEvent.request(ConiumEventType.ENTITY_TICKED);

        EntityType<?> type = entity.getType();

        // Fill the context args.
        tickedContext.put(ConiumEventArgTypes.WORLD, asWorld())
                .put(ConiumEventArgTypes.ENTITY, entity);

        if (tickedContext.presaging(type)) {
            // Only presaging state is true can be continued.
            tickedContext.arising(type);
        }
    }

    @Redirect(
            method = "tickFluid",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/fluid/FluidState;onScheduledTick(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V"
            )
    )
    private void scheduledFluidTick(@NotNull FluidState instance, ServerWorld world, BlockPos pos, BlockState blockState) {
        // Request the fluid ticking context.
        ConiumArisingEventContext<?, ?> tickingContext = ConiumEvent.request(ConiumEventType.FLUID_SCHEDULE_TICK);

        Fluid fluid = instance.getFluid();

        // Fill the context args.
        tickingContext.put(ConiumEventArgTypes.WORLD, world)
                .put(ConiumEventArgTypes.SCHEDULED_TICK_VIEW, world)
                .put(ConiumEventArgTypes.BLOCK_POS, pos)
                .put(ConiumEventArgTypes.BLOCK_STATE, blockState)
                .put(ConiumEventArgTypes.FLUID_STATE, instance);

        if (tickingContext.presaging(fluid)) {
            // Only presaging state is true can be continued.
            tickingContext.arising(fluid);

            instance.onScheduledTick(world, pos, blockState);

            // Request the fluid ticked context.
            ConiumArisingEventContext<?, ?> tickedContext = ConiumEvent.request(ConiumEventType.FLUID_SCHEDULE_TICKED);

            tickedContext.inherit(tickingContext);

            // The ticked context cannot to cancels.
            if (tickedContext.presaging(fluid)) {
                tickedContext.arising(fluid);
            }
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
        // Request the block ticking context.
        ConiumArisingEventContext<?, ?> tickingContext = ConiumEvent.request(ConiumEventType.BLOCK_SCHEDULE_TICK);

        Block block = instance.getBlock();

        // Fill the context args.
        tickingContext.put(ConiumEventArgTypes.WORLD, world)
                .put(ConiumEventArgTypes.SCHEDULED_TICK_VIEW, world)
                .put(ConiumEventArgTypes.BLOCK_POS, pos)
                .put(ConiumEventArgTypes.BLOCK_STATE, instance)
                .put(ConiumEventArgTypes.RANDOM, random);

        if (tickingContext.presaging(block)) {
            // Only presaging state is true can be continued.
            tickingContext.arising(block);

            instance.scheduledTick(world, pos, random);

            // Request the block ticked context.
            ConiumArisingEventContext<?, ?> tickedContext = ConiumEvent.request(ConiumEventType.BLOCK_SCHEDULE_TICKED);

            tickedContext.inherit(tickingContext);

            // The ticked context cannot to cancels.
            if (tickedContext.presaging(block)) {
                tickedContext.arising(block);
            }
        }
    }
}
