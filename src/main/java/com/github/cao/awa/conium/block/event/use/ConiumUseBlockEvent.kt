package com.github.cao.awa.conium.block.event.use

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.block.AbstractBlock.AbstractBlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumUseBlockEvent : ConiumEvent<ParameterSelective5<Boolean, World, PlayerEntity, BlockPos, AbstractBlockState, BlockHitResult>>() {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.BLOCK_HIT_RESULT
        ).attach(
            forever(ConiumEventType.USE_BLOCK)
        ).arise { identity: Any, world: World, player: PlayerEntity, blockPos: BlockPos, blockState: AbstractBlockState, hitResult: BlockHitResult ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, player, blockPos, blockState, hitResult)
            }
        }
    }
}
