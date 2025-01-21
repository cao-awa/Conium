package com.github.cao.awa.conium.block.event.breaking

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.block.AbstractBlock.AbstractBlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumBreakingBlockEvent : ConiumEvent<ParameterSelective4<Boolean, World, PlayerEntity, BlockPos, AbstractBlockState>>(ConiumEventType.BREAKING_BLOCK) {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.BLOCK_STATE
        ).arise { identity: Any, world: World, player: PlayerEntity, blockPos: BlockPos, state: AbstractBlockState ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, player, blockPos, state)
            }
        }
    }
}
