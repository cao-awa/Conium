package com.github.cao.awa.conium.kotlin.extent.item

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.item.builder.conium.ConiumSchemaItemBuilder
import com.github.cao.awa.conium.item.builder.bedrock.BedrockSchemaItemBuilder
import com.github.cao.awa.conium.mixin.item.setting.ItemSettingsAccessor
import net.minecraft.component.ComponentMap
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

fun ConiumSchemaItemBuilder.register() {
    Items.register(itemKeyOf(this.identifier)) {
        build(it)
    }
}

fun BedrockSchemaItemBuilder.register() {
    Items.register(itemKeyOf(this.identifier)) {
        build(it)
    }
}

fun ConiumBlockBuilder.registerBlockItem(block: ConiumBlock) {
    Items.register(itemKeyOf(this.identifier)) {
        BlockItem(block, it)
    }
}

fun itemKeyOf(id: Identifier): RegistryKey<Item> {
    return RegistryKey.of(RegistryKeys.ITEM, id)
}

val Item.Settings.components: ComponentMap.Builder get() = (this as ItemSettingsAccessor).components

