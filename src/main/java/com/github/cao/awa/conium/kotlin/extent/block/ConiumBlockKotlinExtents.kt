package com.github.cao.awa.conium.kotlin.extent.block

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.item.builder.ConiumItemBuilder
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

fun ConiumBlockBuilder.register(afterAction: (ConiumBlock) -> Unit) {
    afterAction(Blocks.register(blockKeyOf(this.identifier), { build(it) }, AbstractBlock.Settings.create()) as ConiumBlock)
}

fun blockKeyOf(id: Identifier): RegistryKey<Block> {
    return RegistryKey.of(RegistryKeys.BLOCK, id)
}
