package com.github.cao.awa.conium.mixin.client.play;

import com.github.cao.awa.conium.intermediary.chunk.ConiumChunkEventMixinIntermediary;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket;
import net.minecraft.world.level.chunk.LevelChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(
            method = "handleLevelChunkWithLight",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onReceiveChunk(ClientboundLevelChunkWithLightPacket packet, CallbackInfo ci) {
        // Trigger receiving chunk event.
        if (ConiumChunkEventMixinIntermediary.fireReceiveChunkEvent(
                packet
        )) {
            // Cancel this event when intermediary was rejected the event.
            ci.cancel();
        }
    }

    @Inject(
            method = "enableChunkLight",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onReceivedChunk(LevelChunk chunk, int x, int z, CallbackInfo ci) {
        // Trigger receiving chunk event.
        if (ConiumChunkEventMixinIntermediary.fireReceivedChunkEvent(
                chunk
        )) {
            // Cancel this event when intermediary was rejected the event.
            ci.cancel();
        }
    }
}
