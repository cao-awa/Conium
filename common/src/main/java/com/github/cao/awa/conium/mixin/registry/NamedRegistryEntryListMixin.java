package com.github.cao.awa.conium.mixin.registry;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(RegistryEntryList.Named.class)
public interface NamedRegistryEntryListMixin<T> {
    @Invoker
    void invokeSetEntries(List<RegistryEntry<T>> entries);
}
