package com.github.cao.awa.conium.kotlin.extent.block

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

fun ConiumBlockBuilder.register(afterAction: (ConiumBlock) -> Unit) {
    afterAction(
        Blocks.register(
            blockKeyOf(this.identifier),
            {
                build(
                    ConiumBlockSettings.create(
                        this.templates,
                        it
                    )
                )
            },
            AbstractBlock.Settings.create()
        ) as ConiumBlock
    )
}

fun blockKeyOf(id: Identifier): RegistryKey<Block> {
    return RegistryKey.of(RegistryKeys.BLOCK, id)
}
