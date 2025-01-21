package com.github.cao.awa.conium.registry.extend;

public interface ConiumDynamicIdList<T> {
    default void clearDynamic() {
        conium$clearDynamic();
    }

    void conium$clearDynamic();

    default void addDynamic(T value) {
        conium$add(value);
    }

    void conium$add(T value);
}
