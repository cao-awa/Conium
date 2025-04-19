package com.github.cao.awa.conium.registry.extend;

import net.minecraft.registry.entry.RegistryEntry;

public interface ConiumDynamicRegistryEntryDelegate<T> {
    default RegistryEntry.Reference<T> getRegistryReference() {
        return conium$getRegistryReference();
    }

    RegistryEntry.Reference<T> conium$getRegistryReference();

    default void setRegistryReference(RegistryEntry.Reference<T> reference) {
        conium$setRegistryReference(reference);
    }

    void conium$setRegistryReference(RegistryEntry.Reference<T> reference);
}