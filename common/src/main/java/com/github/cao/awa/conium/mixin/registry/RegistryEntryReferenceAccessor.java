package com.github.cao.awa.conium.mixin.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Collection;
import java.util.Set;

@Mixin(Holder.Reference.class)
public interface RegistryEntryReferenceAccessor<T> {
    @Invoker("bindKey")
    void registryKey(ResourceKey<T> registryKey);

    @Accessor("tags")
    Set<TagKey<T>> getTags();

    @Invoker("bindTags")
    void tags(Collection<TagKey<T>> tags);

    @Invoker("bindValue")
    void value(T value);
}
