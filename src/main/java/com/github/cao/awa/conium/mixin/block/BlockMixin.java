package com.github.cao.awa.conium.mixin.block;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(
            method = "onBreak",
            at = @At("HEAD")
    )
    public void breakBlock(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfoReturnable<BlockState> cir) {
        ConiumEventContext<?> eventContext = ConiumEvent.request(ConiumEventType.BREAK_BLOCK);

        // Fill the context args.
        eventContext.put(ConiumEventArgTypes.WORLD, world);

        eventContext.put(ConiumEventArgTypes.PLAYER, player);

        eventContext.put(ConiumEventArgTypes.BLOCK_POS, pos);
        eventContext.put(ConiumEventArgTypes.BLOCK_STATE, state);

        if (eventContext.presaging(this)) {
            eventContext.arising(this);
        } else {
            // Cancel this event when presaging was rejected the event.
            cir.cancel();
        }
    }
}
