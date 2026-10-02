package com.github.cao.awa.conium.registry.extend;

import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface ConiumDynamicRegistry {
    default void clearDynamic() {
        conium$clearDynamic();
    }

    void conium$clearDynamic();

    default <T> ResourceKey<T> getKey(Identifier identifier) {
        return (ResourceKey<T>) conium$getKey(identifier);
    }

    ResourceKey<?> conium$getKey(Identifier identifier);

    default boolean isPresent(Identifier identifier) {
        return conium$isPresent(identifier);
    }

    boolean conium$isPresent(Identifier identifier);
}
