package com.github.cao.awa.conium.blockentity.event.chest.trapped.opening
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.blockentity.event.chest.trapped.opened.type.ConiumTrappedChestOpenedEventType
import com.github.cao.awa.conium.blockentity.event.chest.trapped.opening.metadata.ConiumTrappedChestOpeningEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.kotlin.extent.innate.isIt
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective6
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.TrappedChestBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.TrappedChestBlockEntity
import net.minecraft.world.level.block.entity.ContainerOpenersCounter
import net.minecraft.world.entity.player.Player
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level

/**
 *
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
class ConiumTrappedChestOpeningEvent : ConiumEvent<Block, ConiumTrappedChestOpeningEventMetadata, ParameterSelective6<Boolean, World, Player, TrappedChestBlockEntity, BlockBehaviour.BlockStateBase, BlockPos, ContainerOpenersCounter>, ConiumTrappedChestOpenedEventType>(
    ConiumEventType.Companion.TRAPPED_CHEST_OPENING,
    { ConiumEventType.Companion.TRAPPED_CHEST_OPENED }
) {
    override fun requirement(): ConiumArisingEventContext<Block, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.BLOCK,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_ENTITY,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.VIEWER_COUNT_MANAGER
        ) { identity: Block,
            world: World,
            player: Player,
            blockEntity: BlockEntity,
            blockState: BlockBehaviour.BlockStateBase,
            blockPos: BlockPos,
            viewerManager: ContainerOpenersCounter ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, player, blockEntity as TrappedChestBlockEntity, blockState, blockPos, viewerManager)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Block>): ConiumTrappedChestOpeningEventMetadata {
        return ConiumTrappedChestOpeningEventMetadata(context)
    }

    override fun attach() {
        // Request using block event, only handle shulker box here.
        ConiumEventContextBuilder.presaging(
            ConiumEventType.Companion.CHEST_OPENING,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_ENTITY,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.VIEWER_COUNT_MANAGER
        ) { block: Block,
            world: World,
            player: Player,
            blockEntity: BlockEntity,
            blockState: BlockState,
            blockPos: BlockPos,
            viewerManager: ContainerOpenersCounter ->
            val trappedContext: ConiumArisingEventContext<*, *> = request(ConiumEventType.Companion.TRAPPED_CHEST_OPENING)

            trappedContext[ConiumEventArgTypes.WORLD] = world
            trappedContext[ConiumEventArgTypes.PLAYER] = player
            trappedContext[ConiumEventArgTypes.BLOCK_ENTITY] = blockEntity
            trappedContext[ConiumEventArgTypes.BLOCK_STATE] = blockState
            trappedContext[ConiumEventArgTypes.BLOCK_POS] = blockPos
            trappedContext[ConiumEventArgTypes.VIEWER_COUNT_MANAGER] = viewerManager

            if (trappedContext.presaging(block)) {
                trappedContext.arising(block)

                return@presaging true
            }

            false
        }.targetTo(TrappedChestBlock::isIt)
    }
}