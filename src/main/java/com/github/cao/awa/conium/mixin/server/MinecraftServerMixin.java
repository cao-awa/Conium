package com.github.cao.awa.conium.mixin.server;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
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
    public void listenTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        ConiumEventContext<?> eventContext = ConiumEvent.request(ConiumEventType.SERVER_TICK);

        eventContext.put(ConiumEventArgTypes.SERVER, cast());

        if (eventContext.presaging(this)) {
            eventContext.arising(this);
        }
    }

    @Unique
    private MinecraftServer cast() {
        return (MinecraftServer) (Object) this;
    }
}
