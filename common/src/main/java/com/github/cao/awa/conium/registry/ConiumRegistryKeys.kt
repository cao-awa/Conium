package com.github.cao.awa.conium.registry

import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.datapack.inject.item.ItemPropertyInject
import com.github.cao.awa.conium.entity.builder.ConiumEntityBuilder
import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import com.github.cao.awa.conium.script.eval.ScriptEval
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier

object ConiumRegistryKeys {
    @JvmStatic
    val ITEM_PROPERTY_INJECT: RegistryKey<Registry<ItemPropertyInject<*>>> = of("property/item")

    @JvmStatic
    val SCRIPT: RegistryKey<Registry<ScriptEval>> = of("script")

    @JvmStatic
    val ITEM: RegistryKey<Registry<ConiumItemBuilder>> = of("item")

    @JvmStatic
    val BLOCK: RegistryKey<Registry<ConiumBlockBuilder>> = of("block")

    @JvmStatic
    val ENTITY: RegistryKey<Registry<ConiumEntityBuilder>> = of("entity")

    @JvmStatic
    val PLACED_FEATURE: RegistryKey<Registry<ConiumItemBuilder>> = of("worldgen/placed_feature")

    private fun <T> of(path: String): RegistryKey<Registry<T>> = RegistryKey.ofRegistry(Identifier.of("conium", path))
}
