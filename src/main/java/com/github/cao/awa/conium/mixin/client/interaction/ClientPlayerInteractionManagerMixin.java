package com.github.cao.awa.conium.mixin.client.interaction;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @Redirect(
            method = "breakBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;onBroken(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V"
            )
    )
    public void breakBlock(Block instance, WorldAccess world, BlockPos pos, BlockState state) {
        ConiumEventContext<?> eventContext = ConiumEvent.request(ConiumEventType.BROKEN_BLOCK);

        // Fill the context args.
        eventContext.put(ConiumEventArgTypes.WORLD, (World) world);

        eventContext.put(ConiumEventArgTypes.BLOCK_POS, pos);
        eventContext.put(ConiumEventArgTypes.BLOCK_STATE, state);

        if (eventContext.presaging(instance)) {
            // Only presaging state is true can be continues.
            instance.onBroken(world, pos, state);

            eventContext.arising(instance);
        }
    }
}
