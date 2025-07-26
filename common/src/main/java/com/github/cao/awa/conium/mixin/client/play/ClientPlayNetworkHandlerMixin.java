package com.github.cao.awa.conium.mixin.client.play;

import com.github.cao.awa.conium.intermediary.chunk.ConiumChunkEventMixinIntermediary;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(
            method = "onChunkData",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onReceiveChunk(ChunkDataS2CPacket packet, CallbackInfo ci) {
        // Trigger receiving chunk event.
        if (ConiumChunkEventMixinIntermediary.fireReceiveChunkEvent(
                packet
        )) {
            // Cancel this event when intermediary was rejected the event.
            ci.cancel();
        }
    }

    @Inject(
            method = "scheduleRenderChunk",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onReceivedChunk(WorldChunk chunk, int x, int z, CallbackInfo ci) {
        // Trigger receiving chunk event.
        if (ConiumChunkEventMixinIntermediary.fireReceivedChunkEvent(
                chunk
        )) {
            // Cancel this event when intermediary was rejected the event.
            ci.cancel();
        }
    }
}
