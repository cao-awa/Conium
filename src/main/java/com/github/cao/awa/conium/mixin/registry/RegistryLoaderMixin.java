package com.github.cao.awa.conium.mixin.registry;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryLoader;
import net.minecraft.util.crash.CrashException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(RegistryLoader.class)
public class RegistryLoaderMixin {
    @Inject(
            method = "writeAndCreateLoadingException",
            at = @At(value = "HEAD")
    )
    private static void writeAndCreateLoadingException(Map<RegistryKey<?>, Exception> exceptions, CallbackInfoReturnable<CrashException> cir) {
        exceptions.forEach((key, e) -> {
            System.out.println("Error while loading key: " + key);
            e.printStackTrace();
        });
    }
}
