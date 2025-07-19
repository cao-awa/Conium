package com.github.cao.awa.conium.block.event.use

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.block.AbstractBlock.AbstractBlockState
import net.minecraft.block.Block
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumUseBlockEvent : ConiumEvent<Block, ConiumUseBlockEventMetadata, ParameterSelective5<Boolean, World, PlayerEntity, BlockPos, AbstractBlockState, BlockHitResult>>(
    ConiumEventType.USE_BLOCK
) {
    override fun requirement(): ConiumArisingEventContext<Block, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.BLOCK,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.BLOCK_HIT_RESULT
        ) { identity: Any, world: World, player: PlayerEntity, blockPos: BlockPos, blockState: AbstractBlockState, hitResult: BlockHitResult ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, player, blockPos, blockState, hitResult)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Block>): ConiumUseBlockEventMetadata {
        return ConiumUseBlockEventMetadata(context)
    }
}
