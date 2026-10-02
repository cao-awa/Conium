package com.github.cao.awa.conium.registry

import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.datapack.inject.item.ItemPropertyInject
import com.github.cao.awa.conium.entity.builder.ConiumEntityBuilder
import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import com.github.cao.awa.conium.script.eval.ScriptEval
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier

object ConiumRegistryKeys {
    @JvmStatic
    val ITEM_PROPERTY_INJECT: ResourceKey<Registry<ItemPropertyInject<*>>> = of("property/item")

    @JvmStatic
    val SCRIPT: ResourceKey<Registry<ScriptEval>> = of("script")

    @JvmStatic
    val ITEM: ResourceKey<Registry<ConiumItemBuilder>> = of("item")

    @JvmStatic
    val BLOCK: ResourceKey<Registry<ConiumBlockBuilder>> = of("block")

    @JvmStatic
    val ENTITY: ResourceKey<Registry<ConiumEntityBuilder>> = of("entity")

    @JvmStatic
    val PLACED_FEATURE: ResourceKey<Registry<ConiumItemBuilder>> = of("worldgen/placed_feature")

    private fun <T : Any> of(path: String): ResourceKey<Registry<T>> = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("conium", path))
}
