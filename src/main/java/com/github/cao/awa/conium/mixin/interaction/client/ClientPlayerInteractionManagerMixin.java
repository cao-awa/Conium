package com.github.cao.awa.conium.mixin.interaction.client;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @Redirect(
            method = "breakBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;onBreak(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/player/PlayerEntity;)Lnet/minecraft/block/BlockState;"
            )
    )
    public BlockState breakBlock(Block instance, World world, BlockPos pos, BlockState state, PlayerEntity player) {
        ConiumEventContext<?, Boolean> eventContext = ConiumEvent.request(ConiumEventType.BREAK_BLOCK);

        eventContext.put(ConiumEventArgTypes.WORLD, world);

        eventContext.put(ConiumEventArgTypes.PLAYER, player);

        eventContext.put(ConiumEventArgTypes.BLOCK_POS, pos);
        eventContext.put(ConiumEventArgTypes.BLOCK_STATE, state);

        BlockState result = state;

        if (eventContext.presaging(this)) {
            if (eventContext.arising(this)) {
                result = instance.onBreak(world, pos, state, player);
            }
        }

        return result;
    }
}
