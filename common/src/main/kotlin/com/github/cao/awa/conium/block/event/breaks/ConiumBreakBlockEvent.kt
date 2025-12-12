package com.github.cao.awa.conium.block.event.breaks

import com.github.cao.awa.conium.block.event.breaks.metadata.ConiumBreakBlockEventMetadata
import com.github.cao.awa.conium.block.event.broken.type.ConiumBrokenBlockEventType
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective3
import net.minecraft.block.Block
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumBreakBlockEvent : ConiumEvent<Block, ConiumBreakBlockEventMetadata, ParameterSelective3<Boolean, World, PlayerEntity, BlockPos>, ConiumBrokenBlockEventType>(
    ConiumEventType.BREAK_BLOCK,
    { ConiumEventType.BROKEN_BLOCK }
) {
    override fun requirement(): ConiumArisingEventContext<Block, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.BLOCK,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_POS
        ).arise { identity: Block, world: World, player: PlayerEntity, blockPos: BlockPos ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, player, blockPos)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Block>): ConiumBreakBlockEventMetadata {
        return ConiumBreakBlockEventMetadata(context)
    }
}