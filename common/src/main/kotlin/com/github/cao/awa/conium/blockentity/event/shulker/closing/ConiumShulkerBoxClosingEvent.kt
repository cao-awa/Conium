package com.github.cao.awa.conium.blockentity.event.shulker.closing

import com.github.cao.awa.conium.blockentity.event.shulker.closed.type.ConiumShulkerBoxClosedEventType
import com.github.cao.awa.conium.blockentity.event.shulker.closing.metadata.ConiumShulkerBoxClosingEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.block.AbstractBlock.AbstractBlockState
import net.minecraft.block.Block
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
class ConiumShulkerBoxClosingEvent : ConiumEvent<Block, ConiumShulkerBoxClosingEventMetadata, ParameterSelective5<Boolean, World, PlayerEntity, ShulkerBoxBlockEntity, AbstractBlockState, BlockPos>, ConiumShulkerBoxClosedEventType>(
    ConiumEventType.SHULKER_BOX_CLOSING,
    { ConiumEventType.SHULKER_BOX_CLOSED }
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

    override fun metadata(context: ConiumEventContext<Block>): ConiumShulkerBoxClosingEventMetadata {
        return ConiumShulkerBoxClosingEventMetadata(context)
    }
}
