package com.github.cao.awa.conium.registry.extend;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface ConiumDynamicRegistry {
    default void clearDynamic() {
        conium$clearDynamic();
    }

    void conium$clearDynamic();

    default <T> RegistryKey<T> getKey(Identifier identifier) {
        return (RegistryKey<T>) conium$getKey(identifier);
    }

    RegistryKey<?> conium$getKey(Identifier identifier);

    default boolean isPresent(Identifier identifier) {
        return conium$isPresent(identifier);
    }

    boolean conium$isPresent(Identifier identifier);
}
