package com.github.cao.awa.conium.kotlin.extent.block

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.builder.ConiumBlockBuilder
import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.mixin.block.AbstractBlockAccessor
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.entity.player.Player
import net.minecraft.resources.ResourceKey
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.core.Registry
import net.minecraft.world.InteractionResult
import net.minecraft.resources.Identifier
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level

fun ConiumBlockBuilder.register(afterAction: (ConiumBlock) -> Unit) {
    afterAction(
        registerBlock(
            this.identifier
        ) { settings: BlockBehaviour.Properties ->
            build(
                ConiumBlockSettings.create(
                    this.templates.values,
                    settings
                )
            )
        } as ConiumBlock
    )
}

fun registerBlock(identifier: Identifier, blockProvider: (BlockBehaviour.Properties) -> Block): Block {
    val key = blockKeyOf(identifier)
    val properties = BlockBehaviour.Properties.of().setId(key)
    val block = blockProvider(properties)
    return Registry.register(BuiltInRegistries.BLOCK, key, block)
}

fun blockKeyOf(id: Identifier): ResourceKey<Block> = ResourceKey.create(Registries.BLOCK, id)

fun BlockBehaviour.invokeOnUse(
    blockState: BlockState,
    world: Level,
    blockPos: BlockPos,
    playerEntity: Player,
    blockHitResult: BlockHitResult
): InteractionResult = (this as AbstractBlockAccessor).invokeOnUse(
    blockState,
    world,
    blockPos,
    playerEntity,
    blockHitResult
)
