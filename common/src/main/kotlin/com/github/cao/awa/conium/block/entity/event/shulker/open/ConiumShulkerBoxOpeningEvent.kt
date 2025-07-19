package com.github.cao.awa.conium.block.entity.event.shulker.open

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.kotlin.extent.innate.isIt
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.block.AbstractBlock.AbstractBlockState
import net.minecraft.block.Block
import net.minecraft.block.ShulkerBoxBlock
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.ShulkerBoxBlockEntity
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
class ConiumShulkerBoxOpeningEvent : ConiumEvent<Block, ConiumShulkerBoxOpeningEventMetadata, ParameterSelective5<Boolean, World, PlayerEntity, ShulkerBoxBlockEntity, AbstractBlockState, BlockPos>>(
    ConiumEventType.SHULKER_BOX_OPENING
) {
    override fun requirement(): ConiumArisingEventContext<Block, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.BLOCK,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_ENTITY,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.BLOCK_POS
        ) { identity: Block, world: World, pos: PlayerEntity, blockEntity: BlockEntity, blockState: AbstractBlockState, blockPos: BlockPos ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, pos, blockEntity as ShulkerBoxBlockEntity, blockState, blockPos)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Block>): ConiumShulkerBoxOpeningEventMetadata {
        return ConiumShulkerBoxOpeningEventMetadata(context)
    }

    override fun attach() {
        // Request using block event, only handle shulker box here.
        ConiumEventContextBuilder.preRequest(
            ConiumEventType.USE_BLOCK,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_ENTITY
        ) { block: Block, pos: BlockPos, player: PlayerEntity, blockEntity: BlockEntity ->
            if (blockEntity is ShulkerBoxBlockEntity && blockEntity.world != null && !blockEntity.isRemoved && block == blockEntity.cachedState.block) {
                // Request the opening shulker box context.
                val openingContext: ConiumArisingEventContext<*, *> = request(ConiumEventType.SHULKER_BOX_OPENING)

                // Fill context args.
                openingContext.put(ConiumEventArgTypes.BLOCK_POS, pos)
                    .put(ConiumEventArgTypes.BLOCK_ENTITY, blockEntity)
                    .put(ConiumEventArgTypes.BLOCK_STATE, blockEntity.cachedState)
                    .put(ConiumEventArgTypes.WORLD, blockEntity.world!!)
                    .put(ConiumEventArgTypes.PLAYER, player)

                if (openingContext.presaging(block)) {
                    openingContext.arising(block)

                    return@preRequest true
                }

                // Do not real happens opening shulker box when presaging has canceled event.
                return@preRequest false
            }

            true
        }.targetTo(ShulkerBoxBlock::isIt)
    }
}
