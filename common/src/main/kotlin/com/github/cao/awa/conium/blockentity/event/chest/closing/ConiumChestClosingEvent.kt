package com.github.cao.awa.conium.blockentity.event.chest.closing

import com.github.cao.awa.conium.blockentity.event.chest.closed.type.ConiumChestClosedEventType
import com.github.cao.awa.conium.blockentity.event.chest.closing.metadata.ConiumChestClosingEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective6
import net.minecraft.block.AbstractBlock.AbstractBlockState
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
class ConiumChestClosingEvent : ConiumEvent<Block, ConiumChestClosingEventMetadata, ParameterSelective6<Boolean, World, PlayerEntity, ChestBlockEntity, AbstractBlockState, BlockPos, ViewerCountManager>, ConiumChestClosedEventType>(
    ConiumEventType.CHEST_CLOSING,
    { ConiumEventType.CHEST_CLOSED }
) {
    override fun requirement(): ConiumArisingEventContext<Block, out ParameterSelective> {
        return requires(
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
                  blockState: AbstractBlockState,
                  blockPos: BlockPos,
                  viewerManager: ViewerCountManager ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, player, blockEntity as ChestBlockEntity, blockState, blockPos, viewerManager)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Block>): ConiumChestClosingEventMetadata {
        return ConiumChestClosingEventMetadata(context)
    }
}
