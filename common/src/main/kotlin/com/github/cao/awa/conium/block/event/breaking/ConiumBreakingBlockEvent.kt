package com.github.cao.awa.conium.block.event.breaking
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.block.event.breaking.metadata.ConiumBreakingBlockEventMetadata
import com.github.cao.awa.conium.block.event.breaks.type.ConiumBreakBlockEventType
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase
import net.minecraft.world.level.block.Block
import net.minecraft.world.entity.player.Player
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level

class ConiumBreakingBlockEvent : ConiumEvent<Block, ConiumBreakingBlockEventMetadata, ParameterSelective4<Boolean, World, Player, BlockPos, AbstractBlockState>, ConiumBreakBlockEventType>(
    ConiumEventType.BREAKING_BLOCK,
    { ConiumEventType.BREAK_BLOCK }
) {
    override fun requirement(): ConiumArisingEventContext<Block, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.BLOCK,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.BLOCK_STATE
        ) { identity: Block, world: World, player: Player, blockPos: BlockPos, state: AbstractBlockState ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, player, blockPos, state)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Block>): ConiumBreakingBlockEventMetadata {
        return ConiumBreakingBlockEventMetadata(context)
    }
}
