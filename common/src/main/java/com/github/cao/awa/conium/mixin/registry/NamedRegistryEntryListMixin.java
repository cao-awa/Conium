package com.github.cao.awa.conium.mixin.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(HolderSet.Named.class)
public interface NamedRegistryEntryListMixin<T> {
    @Invoker("bind")
    void invokeSetEntries(List<Holder<T>> entries);
}
