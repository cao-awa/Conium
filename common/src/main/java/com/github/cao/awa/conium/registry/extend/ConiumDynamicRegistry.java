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
    default <T> T replace(Identifier id, Supplier<T> value) {
        return conium$replace(id, value);
    }

    <T> T conium$replace(Identifier id, Supplier<T> value);

    default boolean isReplacing() {
        return conium$isReplacing();
    }

    boolean conium$isReplacing();

    default <T> RegistryEntry.Reference<T> getReplacingEntry() {
        return (RegistryEntry.Reference<T>) conium$getReplacingEntry();
    }

    RegistryEntry.Reference<?>  conium$getReplacingEntry();

    default <T> RegistryKey<T> getKey(Identifier identifier) {
        return (RegistryKey<T>) conium$getKey(identifier);
    }

    RegistryKey<?> conium$getKey(Identifier identifier);

    default boolean isPresent(Identifier identifier) {
        return conium$isPresent(identifier);
    }

    boolean conium$isPresent(Identifier identifier);
}
