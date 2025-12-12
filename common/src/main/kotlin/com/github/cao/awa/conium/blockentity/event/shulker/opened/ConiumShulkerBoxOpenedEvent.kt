package com.github.cao.awa.conium.blockentity.event.shulker.opened

import com.github.cao.awa.conium.blockentity.event.shulker.opened.metadata.ConiumShulkerBoxOpenedEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.ShulkerBoxBlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 *
 * @see com.github.cao.awa.conium.blockentity.event.shulker.opening.ConiumShulkerBoxOpeningEvent
 * @see com.github.cao.awa.conium.mixin.block.entity.shulker.ShulkerBoxBlockEntityMixin
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
class ConiumShulkerBoxOpenedEvent : ConiumEvent<Block, ConiumShulkerBoxOpenedEventMetadata, ParameterSelective5<Boolean, World, PlayerEntity, ShulkerBoxBlockEntity, AbstractBlock.AbstractBlockState, BlockPos>, ConiumInactiveEventType>(
    ConiumEventType.SHULKER_BOX_OPENED,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<Block, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.BLOCK,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_ENTITY,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.BLOCK_POS
        ) { identity: Block, world: World, pos: PlayerEntity, blockEntity: BlockEntity, blockState: AbstractBlock.AbstractBlockState, blockPos: BlockPos ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, pos, blockEntity as ShulkerBoxBlockEntity, blockState, blockPos)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Block>): ConiumShulkerBoxOpenedEventMetadata {
        return ConiumShulkerBoxOpenedEventMetadata(context)
    }
}