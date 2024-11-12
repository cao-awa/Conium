package com.github.cao.awa.conium.block.event.breaking

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumBreakBlockEvent : ConiumEvent<ParameterSelective4<Boolean, World, PlayerEntity, BlockPos, BlockState>>() {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.BLOCK_STATE
        ).attach(
            forever(ConiumEventType.BREAK_BLOCK)
        ).arise { identity, world, player, blockPos, blockState ->
            noFailure(identity) {
                it.arise(world, player, blockPos, blockState)
            }
        }
    }
}
