package com.github.cao.awa.conium.registry;

public interface ConiumDynamicRegistry {
    default void clearDynamic() {
        conium$clearDynamic();
    }

    void conium$clearDynamic();
}
