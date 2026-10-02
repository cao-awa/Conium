package com.github.cao.awa.conium.blockentity.event.chest.closed
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.blockentity.event.chest.closed.metadata.ConiumChestClosedEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective6
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.ChestBlockEntity
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
class ConiumChestClosedEvent : ConiumEvent<Block, ConiumChestClosedEventMetadata, ParameterSelective6<Boolean, World, Player, ChestBlockEntity, BlockBehaviour.BlockStateBase, BlockPos, ContainerOpenersCounter>, ConiumInactiveEventType>(
    ConiumEventType.CHEST_CLOSED,
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
        ) { identity: Block,
            world: World,
            player: Player,
            blockEntity: BlockEntity,
            blockState: BlockBehaviour.BlockStateBase,
            blockPos: BlockPos,
            viewerManager: ContainerOpenersCounter ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, player, blockEntity as ChestBlockEntity, blockState, blockPos, viewerManager)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Block>): ConiumChestClosedEventMetadata {
        return ConiumChestClosedEventMetadata(context)
    }
}