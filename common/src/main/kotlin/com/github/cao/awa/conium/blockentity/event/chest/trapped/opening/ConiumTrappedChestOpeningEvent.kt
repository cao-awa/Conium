package com.github.cao.awa.conium.blockentity.event.chest.trapped.opening

import com.github.cao.awa.conium.blockentity.event.chest.trapped.opened.type.ConiumTrappedChestOpenedEventType
import com.github.cao.awa.conium.blockentity.event.chest.trapped.opening.metadata.ConiumTrappedChestOpeningEventMetadata
import com.github.cao.awa.conium.blockentity.event.chest.trapped.opening.type.ConiumTrappedChestOpeningEventType
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.kotlin.extent.innate.isIt
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective6
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.TrappedChestBlock
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.TrappedChestBlockEntity
import net.minecraft.block.entity.ViewerCountManager
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 *
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
class ConiumTrappedChestOpeningEvent : ConiumEvent<Block, ConiumTrappedChestOpeningEventMetadata, ParameterSelective6<Boolean, World, PlayerEntity, TrappedChestBlockEntity, AbstractBlock.AbstractBlockState, BlockPos, ViewerCountManager>, ConiumTrappedChestOpenedEventType>(
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
            player: PlayerEntity,
            blockEntity: BlockEntity,
            blockState: AbstractBlock.AbstractBlockState,
            blockPos: BlockPos,
            viewerManager: ViewerCountManager ->
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
        ConiumEventContextBuilder.preRequest(
            ConiumEventType.Companion.CHEST_OPENING,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_ENTITY,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.VIEWER_COUNT_MANAGER
        ) { block: Block,
            world: World,
            player: PlayerEntity,
            blockEntity: BlockEntity,
            blockState: BlockState,
            blockPos: BlockPos,
            viewerManager: ViewerCountManager ->
            val trappedContext: ConiumArisingEventContext<*, *> = request(ConiumEventType.Companion.TRAPPED_CHEST_OPENING)

            trappedContext[ConiumEventArgTypes.WORLD] = world
            trappedContext[ConiumEventArgTypes.PLAYER] = player
            trappedContext[ConiumEventArgTypes.BLOCK_ENTITY] = blockEntity
            trappedContext[ConiumEventArgTypes.BLOCK_STATE] = blockState
            trappedContext[ConiumEventArgTypes.BLOCK_POS] = blockPos
            trappedContext[ConiumEventArgTypes.VIEWER_COUNT_MANAGER] = viewerManager

            if (trappedContext.presaging(block)) {
                trappedContext.arising(block)

                return@preRequest true
            }

            false
        }.targetTo(TrappedChestBlock::isIt)
    }
}