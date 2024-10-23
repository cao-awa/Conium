package com.github.cao.awa.conium.registry;

import com.github.cao.awa.conium.datapack.inject.item.ItemPropertyInject;
import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ConiumRegistryKeys {
    public static final RegistryKey<Registry<ItemPropertyInject<?>>> ITEM_PROPERTY_INJECT = of("property/item");
    public static final RegistryKey<Registry<ItemPropertyInject<?>>> SCRIPTS = of("scripts");
    public static final RegistryKey<Registry<ConiumBlockBuilder>> ITEM = of("item");
    public static final RegistryKey<Registry<ConiumBlockBuilder>> BLOCK = of("block");

    private static <T> RegistryKey<Registry<T>> of(String id) {
        return RegistryKey.ofRegistry(Identifier.of("conium", id));
    }
}
