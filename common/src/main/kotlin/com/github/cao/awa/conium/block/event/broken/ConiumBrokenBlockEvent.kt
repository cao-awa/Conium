package com.github.cao.awa.conium.block.event.broken

import com.github.cao.awa.conium.block.event.broken.metadata.ConiumBrokenBlockEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumBrokenBlockEvent : ConiumEvent<Block, ConiumBrokenBlockEventMetadata, ParameterSelective4<Boolean, World, PlayerEntity, BlockPos, AbstractBlock.AbstractBlockState>, ConiumInactiveEventType>(
    ConiumEventType.BROKEN_BLOCK,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<Block, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.BLOCK,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.BLOCK_STATE
        ) { identity: Block, world: World, player: PlayerEntity, blockPos: BlockPos, state: AbstractBlock.AbstractBlockState ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, player, blockPos, state)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Block>): ConiumBrokenBlockEventMetadata {
        return ConiumBrokenBlockEventMetadata(context)
    }
}