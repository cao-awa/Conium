package com.github.cao.awa.conium.kotlin.extent.item

import com.github.cao.awa.conium.Conium
import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

fun ConiumItemBuilder.register() {
    Items.register(itemKeyOf(this.identifier)) {
        build(it)
    }.let {
        Conium.coniumItemManager!!.loadTags(this.identifier)
    }
}

fun ConiumBlockBuilder.registerBlockItem(block: ConiumBlock) {
    Items.register(itemKeyOf(this.identifier)) {
        BlockItem(block, it)
    }.let {
        Conium.coniumItemManager!!.loadTags(this.identifier)
    }
}

fun itemKeyOf(id: Identifier): RegistryKey<Item> {
    return RegistryKey.of(RegistryKeys.ITEM, id)
}
