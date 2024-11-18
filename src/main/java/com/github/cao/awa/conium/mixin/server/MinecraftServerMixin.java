package com.github.cao.awa.conium.mixin.server;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    public void tickStart(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        // Request the server ticking context.
        ConiumEventContext<?> tickingContext = ConiumEvent.request(ConiumEventType.SERVER_TICK);

        tickingContext.put(ConiumEventArgTypes.SERVER, cast());

        if (tickingContext.presaging(this)) {
            tickingContext.arising(this);
        }
    }

    @Inject(
            method = "tick",
            at = @At("TAIL")
    )
    public void tickTail(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        // Request the server ticked context.
        ConiumEventContext<?> tickedContext = ConiumEvent.request(ConiumEventType.SERVER_TICK_TAIL);

        tickedContext.put(ConiumEventArgTypes.SERVER, cast());

        if (tickedContext.presaging(this)) {
            tickedContext.arising(this);
        }
    }

    @Unique
    private MinecraftServer cast() {
        return (MinecraftServer) (Object) this;
    }
}
