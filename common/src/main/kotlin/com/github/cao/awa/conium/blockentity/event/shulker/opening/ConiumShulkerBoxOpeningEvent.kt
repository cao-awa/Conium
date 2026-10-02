package com.github.cao.awa.conium.blockentity.event.shulker.opening
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.blockentity.event.shulker.opened.type.ConiumShulkerBoxOpenedEventType
import com.github.cao.awa.conium.blockentity.event.shulker.opening.metadata.ConiumShulkerBoxOpeningEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.kotlin.extent.innate.isIt
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.ShulkerBoxBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity
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
class ConiumShulkerBoxOpeningEvent : ConiumEvent<Block, ConiumShulkerBoxOpeningEventMetadata, ParameterSelective5<Boolean, World, Player, ShulkerBoxBlockEntity, AbstractBlockState, BlockPos>, ConiumShulkerBoxOpenedEventType>(
    ConiumEventType.SHULKER_BOX_OPENING,
    { ConiumEventType.SHULKER_BOX_OPENED }
) {
    override fun requirement(): ConiumArisingEventContext<Block, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.BLOCK,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_ENTITY,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.BLOCK_POS
        ) { identity: Block, world: World, pos: Player, blockEntity: BlockEntity, blockState: AbstractBlockState, blockPos: BlockPos ->
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
        ConiumEventContextBuilder.presaging(
            ConiumEventType.USE_BLOCK,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_ENTITY
        ) { block: Block, pos: BlockPos, player: Player, blockEntity: BlockEntity ->
            if (blockEntity is ShulkerBoxBlockEntity && blockEntity.level != null && !blockEntity.isRemoved && block == blockEntity.blockState.block) {
                // Request the opening shulker box context.
                val openingContext: ConiumArisingEventContext<*, *> = request(ConiumEventType.SHULKER_BOX_OPENING)

                // Fill context args.
                openingContext.put(ConiumEventArgTypes.BLOCK_POS, pos)
                    .put(ConiumEventArgTypes.BLOCK_ENTITY, blockEntity)
                    .put(ConiumEventArgTypes.BLOCK_STATE, blockEntity.blockState)
                    .put(ConiumEventArgTypes.WORLD, blockEntity.level!!)
                    .put(ConiumEventArgTypes.PLAYER, player)

                if (openingContext.presaging(block)) {
                    openingContext.arising(block)

                    return@presaging true
                }

                // Do not real happens opening shulker box when presaging has canceled event.
                return@presaging false
            }

            true
        }.targetTo(ShulkerBoxBlock::isIt)
    }
}
