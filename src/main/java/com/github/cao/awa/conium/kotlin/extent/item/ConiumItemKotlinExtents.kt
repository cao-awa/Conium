package com.github.cao.awa.conium.kotlin.extent.item

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.block.builder.conium.ConiumSchemaBlockBuilder
import com.github.cao.awa.conium.item.builder.conium.ConiumSchemaItemBuilder
import com.github.cao.awa.conium.item.builder.bedrock.BedrockSchemaItemBuilder
import com.github.cao.awa.conium.kotlin.extent.registry.tags
import com.github.cao.awa.conium.mixin.item.setting.ItemSettingsAccessor
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.component.ComponentMap
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

fun ConiumSchemaItemBuilder.register(tagProvider: (MutableSet<TagKey<Item>>) -> Unit = { }) {
    registerItem(this.identifier, ::build).let { item ->
        item.registryEntry
            .tags
            .let {
                CollectionFactor.hashSet<TagKey<Item>>().also { newTags ->
                    newTags.addAll(it)
                }
            }
            .also(tagProvider)
            .also {
                // TODO
//                item.registryEntry.tags = it
            }
    }
}

fun BedrockSchemaItemBuilder.register(tagProvider: (RegistryEntry.Reference<Item>) -> Unit = { }) {
    tagProvider(registerItem(this.identifier, ::build).registryEntry)
}

fun registerItem(identifier: Identifier, itemProvider: (Item.Settings) -> Item): Item {
    return Items.register(itemKeyOf(identifier)) {
        itemProvider(it)
    }
}

fun ConiumBlockBuilder.registerBlockItem(block: ConiumBlock, settingsProvider: (Item.Settings) -> Unit = { }) {
    Items.register(itemKeyOf(this.identifier)) {
        settingsProvider(it)
        BlockItem(block, it)
    }
}

fun itemKeyOf(id: Identifier): RegistryKey<Item> {
    return RegistryKey.of(RegistryKeys.ITEM, id)
}

val Item.Settings.components: ComponentMap.Builder get() = (this as ItemSettingsAccessor).components

