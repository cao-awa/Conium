package com.github.cao.awa.conium.mixin.registry;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Collection;
import java.util.Set;

@Mixin(RegistryEntry.Reference.class)
public interface RegistryEntryReferenceMixin<T> {
    @Invoker("setRegistryKey")
    void registryKey(RegistryKey<T> registryKey);

    @Accessor("tags")
    Set<TagKey<T>> getTags();

    @Invoker("setTags")
    void tags(Collection<TagKey<T>> tags);

    @Invoker("setValue")
    void value(T value);
}
