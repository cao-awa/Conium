package com.github.cao.awa.conium.mixin.server;

import com.github.cao.awa.conium.event.ConiumEvent;
import com.github.cao.awa.conium.event.context.ConiumEventContext;
import com.github.cao.awa.conium.event.type.ConiumEventType;
import com.github.cao.awa.conium.intermediary.mixin.server.ConiumServerEventMixinIntermediary;
import com.github.cao.awa.conium.random.ConiumRandom;
import com.github.cao.awa.sinuatum.manipulate.Manipulate;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
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
        // Trigger server tick event.
        ConiumServerEventMixinIntermediary.fireServerTickEvent(Manipulate.cast(this));

        // Trigger random task on server.
        if (ConiumRandom.nextBoolean()) {
            ConiumServerEventMixinIntermediary.fireServerRandomEvent(Manipulate.cast(this));
        }
    }

    @Inject(
            method = "tick",
            at = @At("TAIL")
    )
    public void tickTail(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        // Trigger server tick tail event.
        ConiumServerEventMixinIntermediary.fireServerTickTailEvent(Manipulate.cast(this));
    }
}
