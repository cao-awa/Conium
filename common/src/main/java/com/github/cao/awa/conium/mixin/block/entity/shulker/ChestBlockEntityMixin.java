package com.github.cao.awa.conium.mixin.block.entity.shulker;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChestBlockEntity.class)
public abstract class ChestBlockEntityMixin extends RandomizableContainerBlockEntity {
    protected ChestBlockEntityMixin(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Redirect(
            method = "startOpen",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/entity/ContainerOpenersCounter;incrementOpeners(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;D)V"
            )
    )
    public void onOpenChest(ContainerOpenersCounter instance, LivingEntity user, Level world, BlockPos pos, BlockState state, double userInteractionRange) {
        // Request the opening chest context.
        ConiumArisingEventContext<?, ?> openingContext = buildContext(
                ConiumEventType.CHEST_OPENING,
                instance,
                user,
                world,
                pos,
                state
        );

        Block block = getBlockState().getBlock();

        if (openingContext.presaging(block)) {
            openingContext.arising(block);

            // Request the opened chest context.
            ConiumArisingEventContext<?, ?> openedContext = ConiumEvent.request(ConiumEventType.CHEST_OPENED);

            openedContext.inherit(openingContext);

            // Opened event cannot cancel because it already completed.
            if (openedContext.presaging(block)) {
                openedContext.arising(block);
            }
        }
    }

    @Redirect(
            method = "stopOpen",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/entity/ContainerOpenersCounter;decrementOpeners(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V"
            )
    )
    public void onCloseChest(ContainerOpenersCounter instance, LivingEntity user, Level world, BlockPos pos, BlockState state) {
        // Request the closing chest context.
        ConiumArisingEventContext<?, ?> closingContext = buildContext(
                ConiumEventType.CHEST_CLOSING,
                instance,
                user,
                world,
                pos,
                state
        );

        Block block = getBlockState().getBlock();

        if (closingContext.presaging(block)) {
            closingContext.arising(block);

            instance.decrementOpeners(user, world, pos, state);

            // Request the closed chest context.
            ConiumArisingEventContext<?, ?> closedContext = ConiumEvent.request(ConiumEventType.CHEST_CLOSED);

            closedContext.inherit(closingContext);

            // Closed event cannot cancel because it already completed.
            if (closedContext.presaging(block)) {
                closedContext.arising(block);
            }
        }
    }

    @Unique
    @NotNull
    private ConiumArisingEventContext<?, ?> buildContext(
            @NotNull ConiumEventType<?, ?, ?, ?> eventType,
            @NotNull ContainerOpenersCounter viewerManager,
            @NotNull LivingEntity user,
            @NotNull Level world,
            @NotNull BlockPos pos,
            @NotNull BlockState state
    ) {
        // Request the event context.
        ConiumArisingEventContext<?, ?> eventContext = ConiumEvent.request(eventType);

        // Fill context args.
        eventContext.put(ConiumEventArgTypes.BLOCK_POS, pos)
                .put(ConiumEventArgTypes.BLOCK_ENTITY, this)
                .put(ConiumEventArgTypes.BLOCK_STATE, state)
                .put(ConiumEventArgTypes.WORLD, world)
                .put(ConiumEventArgTypes.LIVING_ENTITY, user)
                .put(ConiumEventArgTypes.VIEWER_COUNT_MANAGER, viewerManager);

        return eventContext;
    }
}
