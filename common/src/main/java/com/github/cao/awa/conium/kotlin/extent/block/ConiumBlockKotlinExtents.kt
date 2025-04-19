package com.github.cao.awa.conium.kotlin.extent.block

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.mixin.block.AbstractBlockMixin
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.ActionResult
import net.minecraft.util.Identifier
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

fun ConiumBlockBuilder.register(afterAction: (ConiumBlock) -> Unit) {
    afterAction(
        registerBlock(
            this.identifier
        ) { settings: AbstractBlock.Settings ->
            build(
                ConiumBlockSettings.create(
                    this.templates.values,
                    settings
                )
            )
        } as ConiumBlock
    )
}

fun registerBlock(identifier: Identifier, blockProvider: (AbstractBlock.Settings) -> Block): Block {
    return Blocks.register(
        blockKeyOf(identifier),
        blockProvider,
        AbstractBlock.Settings.create()
    )
}

fun blockKeyOf(id: Identifier): RegistryKey<Block> = RegistryKey.of(RegistryKeys.BLOCK, id)

fun AbstractBlock.invokeOnUse(
    blockState: BlockState,
    world: World,
    blockPos: BlockPos,
    playerEntity: PlayerEntity,
    blockHitResult: BlockHitResult
): ActionResult = (this as AbstractBlockMixin).invokeOnUse(
    blockState,
    world,
    blockPos,
    playerEntity,
    blockHitResult
)