package com.github.cao.awa.conium.mixin.client;

import com.github.cao.awa.conium.util.ConiumShutdownGuard;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Arms the shutdown fallback once the client has finished closing.
 *
 * At this point the world has already been saved and disconnected, but the JVM will never leave
 * `DestroyJavaVM` because Architectury Transformer leaks non-daemon pool threads. Minecraft's own
 * shutdown watchdog would force exit with `-8` fifteen seconds later, which surfaces as a crash.
 *
 * @author cao_awa
 * @author 草二号机
 * @since 1.0.0
 */
@Mixin(Minecraft.class)
public class MinecraftClientShutdownMixin {
    @Inject(method = "close", at = @At("RETURN"))
    private void afterClose(CallbackInfo info) {
        ConiumShutdownGuard.install();
    }
}
