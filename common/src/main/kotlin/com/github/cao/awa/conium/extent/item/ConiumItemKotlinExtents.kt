package com.github.cao.awa.conium.kotlin.extent.item

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import com.github.cao.awa.conium.item.builder.conium.ConiumSchemaItemBuilder
import com.github.cao.awa.conium.item.setting.ConiumItemSettings
import net.minecraft.world.level.block.Block
import net.minecraft.core.component.DataComponentMap
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import java.util.WeakHashMap

fun ConiumItemBuilder.register() {
    registerItem(this.identifier) {
        build(ConiumItemSettings(it))
    }
}

fun registerItem(identifier: Identifier, itemProvider: (Item.Properties) -> Item): Item {
    val key = itemKeyOf(identifier)
    val properties = Item.Properties().setId(key)
    val item = itemProvider(properties)
    return Registry.register(BuiltInRegistries.ITEM, identifier, item)
}

fun registerBlockItem(identifier: Identifier, block: Block, settingsProvider: (Item.Properties) -> Unit): Item {
    val key = itemKeyOf(identifier)
    val properties = Item.Properties().setId(key)
    settingsProvider(properties)
    val item = BlockItem(block, properties)
    return Registry.register(BuiltInRegistries.ITEM, identifier, item)
}

fun ConiumBlockBuilder.registerBlockItem(block: ConiumBlock, settingsProvider: (Item.Properties) -> Unit = { }): Item {
    return registerBlockItem(this.identifier, block, settingsProvider)
}

fun itemKeyOf(id: Identifier): ResourceKey<Item> = ResourceKey.create(Registries.ITEM, id)

private val propertiesComponentBuilders = WeakHashMap<Item.Properties, DataComponentMap.Builder>()
val Item.Properties.components: DataComponentMap.Builder
    get() = propertiesComponentBuilders.computeIfAbsent(this) { DataComponentMap.builder() }
