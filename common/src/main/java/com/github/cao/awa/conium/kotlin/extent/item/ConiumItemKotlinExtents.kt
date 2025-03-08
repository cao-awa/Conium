package com.github.cao.awa.conium.kotlin.extent.item

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import com.github.cao.awa.conium.item.builder.conium.ConiumSchemaItemBuilder
import com.github.cao.awa.conium.item.setting.ConiumItemSettings
import com.github.cao.awa.conium.mixin.item.setting.ItemSettingsAccessor
import net.minecraft.block.Block
import net.minecraft.component.ComponentMap
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

fun ConiumItemBuilder.register() {
    registerItem(this.identifier){
        build(ConiumItemSettings(it))
    }
}

fun registerItem(identifier: Identifier, itemProvider: (Item.Settings) -> Item): Item {
    return Items.register(itemKeyOf(identifier)) {
        itemProvider(it)
    }
}

fun registerBlockItem(identifier: Identifier, block: Block, settingsProvider: (Item.Settings) -> Unit): Item {
    return Items.register(itemKeyOf(identifier)) {
        settingsProvider(it)
        BlockItem(block, it)
    }
}

fun ConiumBlockBuilder.registerBlockItem(block: ConiumBlock, settingsProvider: (Item.Settings) -> Unit = { }): Item {
    return registerBlockItem(this.identifier, block, settingsProvider)
}

fun itemKeyOf(id: Identifier): RegistryKey<Item> = RegistryKey.of(RegistryKeys.ITEM, id)

val Item.Settings.components: ComponentMap.Builder get() = (this as ItemSettingsAccessor).components

