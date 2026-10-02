package com.github.cao.awa.conium.mixin.block.entity.shulker;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShulkerBoxBlockEntity.class)
public abstract class ShulkerBoxBlockEntityMixin extends RandomizableContainerBlockEntity implements WorldlyContainer {
    protected ShulkerBoxBlockEntityMixin(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Inject(
            method = "startOpen",
            at = @At("RETURN"),
            cancellable = true
    )
    public void onOpenedShulkerBox(ContainerUser user, CallbackInfo ci) {
        // Do not arise the event when it removed or not in world.
        if (this.level == null || this.isRemoved()) {
            ci.cancel();
            return;
        }

        // Request the opened shulker box context.
        ConiumArisingEventContext<?, ?> openingContext = buildContext(ConiumEventType.SHULKER_BOX_OPENED, user);

        Block block = getBlockState().getBlock();

        // Opened event cannot cancel because it already completed.
        if (openingContext.presaging(block)) {
            openingContext.arising(block);
        }
    }

    @Inject(
            method = "stopOpen",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onClosing(ContainerUser user, CallbackInfo ci) {
        // Do not arise the event when it removed or not in world.
        if (this.level == null || this.isRemoved()) {
            ci.cancel();
            return;
        }

        // Request the closing shulker box context.
        ConiumArisingEventContext<?, ?> closingContext = buildContext(ConiumEventType.SHULKER_BOX_CLOSING, user);

        Block block = getBlockState().getBlock();

        if (closingContext.presaging(block)) {
            closingContext.arising(block);
        } else {
            // Do not real happens opening shulker box when presaging has canceled event.
            ci.cancel();
        }
    }

    @Inject(
            method = "stopOpen",
            at = @At("RETURN"),
            cancellable = true
    )
    public void onClosed(ContainerUser user, CallbackInfo ci) {
        // Do not arise the event when it removed or not in world.
        if (this.level == null || this.isRemoved()) {
            ci.cancel();
            return;
        }

        // Request the closed shulker box context.
        ConiumArisingEventContext<?, ?> closedContext = buildContext(ConiumEventType.SHULKER_BOX_CLOSED, user);

        Block block = getBlockState().getBlock();

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
        assert this.level != null;
        eventContext.put(ConiumEventArgTypes.BLOCK_POS, this.worldPosition)
                .put(ConiumEventArgTypes.BLOCK_ENTITY, this)
                .put(ConiumEventArgTypes.BLOCK_STATE, getBlockState())
                .put(ConiumEventArgTypes.WORLD, this.level)
                .put(ConiumEventArgTypes.CONTAINER_USER, user);

        return eventContext;
    }
}
