package com.github.cao.awa.conium.mixin.block.entity.shulker;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.entity.ViewerCountManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChestBlockEntity.class)
public abstract class ChestBlockEntityMixin extends LootableContainerBlockEntity implements SidedInventory {
    protected ChestBlockEntityMixin(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Redirect(
            method = "onOpen",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/entity/ViewerCountManager;openContainer(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V"
            )
    )
    public void onOpenChest(ViewerCountManager instance, PlayerEntity player, World world, BlockPos pos, BlockState state) {
        // Request the opening chest context.
        ConiumArisingEventContext<?> openingContext = buildContext(
                ConiumEventType.CHEST_OPENING,
                instance,
                player,
                world,
                pos,
                state
        );

        Block block = getCachedState().getBlock();

        if (openingContext.presaging(block)) {
            openingContext.arising(block);

            // Request the closed shulker box context.
            ConiumArisingEventContext<?> openedContext = ConiumEvent.request(ConiumEventType.CHEST_OPENED);

            openedContext.inherit(openingContext);

            // Closed event cannot cancel because it already completed.
            if (openedContext.presaging(block)) {
                openedContext.arising(block);
            }
        }
    }

    @Redirect(
            method = "onClose",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/entity/ViewerCountManager;closeContainer(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V"
            )
    )
    public void onCloseChest(ViewerCountManager instance, PlayerEntity player, World world, BlockPos pos, BlockState state) {
        // Request the closing shulker box context.
        ConiumArisingEventContext<?> closingContext = buildContext(
                ConiumEventType.CHEST_CLOSING,
                instance,
                player,
                world,
                pos,
                state
        );

        Block block = getCachedState().getBlock();

        if (closingContext.presaging(block)) {
            closingContext.arising(block);

            instance.closeContainer(player, world, pos, state);

            // Request the closed chest context.
            ConiumArisingEventContext<?> closedContext = ConiumEvent.request(ConiumEventType.CHEST_CLOSED);

            closedContext.inherit(closingContext);

            // Closed event cannot cancel because it already completed.
            if (closedContext.presaging(block)) {
                closedContext.arising(block);
            }
        }
    }

    @Unique
    @NotNull
    private ConiumArisingEventContext<?> buildContext(
            @NotNull ConiumEventType<?> eventType,
            @NotNull ViewerCountManager viewerManager,
            @NotNull PlayerEntity player,
            @NotNull World world,
            @NotNull BlockPos pos,
            @NotNull BlockState state
    ) {
        // Request the event context.
        ConiumArisingEventContext<?> eventContext = ConiumEvent.request(eventType);

        // Fill context args.
        eventContext.put(ConiumEventArgTypes.BLOCK_POS, pos)
                .put(ConiumEventArgTypes.BLOCK_ENTITY, this)
                .put(ConiumEventArgTypes.BLOCK_STATE, state)
                .put(ConiumEventArgTypes.WORLD, world)
                .put(ConiumEventArgTypes.PLAYER, player)
                .put(ConiumEventArgTypes.VIEWER_COUNT_MANAGER, viewerManager);

        return eventContext;
    }
}
