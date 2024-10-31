package com.github.cao.awa.conium.mixin.registry;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Collection;

@Mixin(RegistryEntry.Reference.class)
public interface RegistryEntryReferenceMixin<T> {
    @Invoker
    void invokeSetRegistryKey(RegistryKey<T> registryKey);
    @Invoker
    void invokeSetTags(Collection<TagKey<T>> tags);
    @Invoker
    void invokeSetValue(T value);
}
