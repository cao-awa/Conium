package com.github.cao.awa.conium.mixin.block.entity.shulker;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.ContainerUser;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShulkerBoxBlockEntity.class)
public abstract class ShulkerBoxBlockEntityMixin extends LootableContainerBlockEntity implements SidedInventory {
    protected ShulkerBoxBlockEntityMixin(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Inject(
            method = "onOpen",
            at = @At("RETURN"),
            cancellable = true
    )
    public void onOpenedShulkerBox(ContainerUser user, CallbackInfo ci) {
        // Do not arise the event when it removed or not in world.
        if (this.world == null || this.removed) {
            ci.cancel();
            return;
        }

        // Request the opened shulker box context.
        ConiumArisingEventContext<?, ?> openingContext = buildContext(ConiumEventType.SHULKER_BOX_OPENED, user);

        Block block = getCachedState().getBlock();

        // Opened event cannot cancel because it already completed.
        if (openingContext.presaging(block)) {
            openingContext.arising(block);
        }
    }

    @Inject(
            method = "onClose",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onClosing(ContainerUser user, CallbackInfo ci) {
        // Do not arise the event when it removed or not in world.
        if (this.world == null || this.removed) {
            ci.cancel();
            return;
        }

        // Request the closing shulker box context.
        ConiumArisingEventContext<?, ?> closingContext = buildContext(ConiumEventType.SHULKER_BOX_CLOSING, user);

        Block block = getCachedState().getBlock();

        if (closingContext.presaging(block)) {
            closingContext.arising(block);
        } else {
            // Do not real happens opening shulker box when presaging has canceled event.
            ci.cancel();
        }
    }

    @Inject(
            method = "onClose",
            at = @At("RETURN"),
            cancellable = true
    )
    public void onClosed(ContainerUser user, CallbackInfo ci) {
        // Do not arise the event when it removed or not in world.
        if (this.world == null || this.removed) {
            ci.cancel();
            return;
        }

        // Request the closed shulker box context.
        ConiumArisingEventContext<?, ?> closedContext = buildContext(ConiumEventType.SHULKER_BOX_CLOSED, user);

        Block block = getCachedState().getBlock();

        // Closed event cannot cancel because it already completed.
        if (closedContext.presaging(block)) {
            closedContext.arising(block);
        }
    }

    @Unique
    @NotNull
    private ConiumArisingEventContext<?, ?> buildContext(@NotNull ConiumEventType<?, ?, ?, ?> eventType, @NotNull ContainerUser user) {
        // Request the event context.
        ConiumArisingEventContext<?, ?> eventContext = ConiumEvent.request(eventType);

        // Fill context args.
        assert this.world != null;
        eventContext.put(ConiumEventArgTypes.BLOCK_POS, this.pos)
                .put(ConiumEventArgTypes.BLOCK_ENTITY, this)
                .put(ConiumEventArgTypes.BLOCK_STATE, getCachedState())
                .put(ConiumEventArgTypes.WORLD, this.world)
                .put(ConiumEventArgTypes.CONTAINER_USER, user);

        return eventContext;
    }
}
