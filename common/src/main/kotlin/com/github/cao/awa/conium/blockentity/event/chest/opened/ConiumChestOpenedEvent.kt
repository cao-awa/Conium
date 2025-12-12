package com.github.cao.awa.conium.blockentity.event.chest.opened

import com.github.cao.awa.conium.blockentity.event.chest.opened.metadata.ConiumChestOpenedEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective6
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.ChestBlockEntity
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
class ConiumChestOpenedEvent : ConiumEvent<Block, ConiumChestOpenedEventMetadata, ParameterSelective6<Boolean, World, PlayerEntity, ChestBlockEntity, AbstractBlock.AbstractBlockState, BlockPos, ViewerCountManager>, ConiumInactiveEventType>(
    ConiumEventType.CHEST_OPENED,
    { ConiumEventType.INACTIVE }
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
        ) { identity: Any,
            world: World,
            player: PlayerEntity,
            blockEntity: BlockEntity,
            blockState: AbstractBlock.AbstractBlockState,
            blockPos: BlockPos,
            viewerManager: ViewerCountManager ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, player, blockEntity as ChestBlockEntity, blockState, blockPos, viewerManager)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Block>): ConiumChestOpenedEventMetadata {
        return ConiumChestOpenedEventMetadata(context)
    }
}