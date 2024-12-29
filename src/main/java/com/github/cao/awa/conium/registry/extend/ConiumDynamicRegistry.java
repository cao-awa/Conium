package com.github.cao.awa.conium.registry.extend;

public interface ConiumDynamicRegistry {
    default void clearDynamic() {
        conium$clearDynamic();
    }

    void conium$clearDynamic();
}
