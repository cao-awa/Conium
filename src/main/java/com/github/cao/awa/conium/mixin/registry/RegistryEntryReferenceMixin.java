package com.github.cao.awa.conium.mixin.registry;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(RegistryEntry.Reference.class)
public interface RegistryEntryReferenceMixin<T> {
    @Invoker
    void invokeSetRegistryKey(RegistryKey<T> registryKey);
}
