package com.github.cao.awa.conium.block.event.use

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective6
import net.minecraft.block.AbstractBlock.AbstractBlockState
import net.minecraft.block.Block
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumUsedBlockEvent : ConiumEvent<Block, ConiumUsedBlockEventMetadata, ParameterSelective6<Boolean, World, PlayerEntity, BlockPos, AbstractBlockState, BlockHitResult, ActionResult>>(
    ConiumEventType.USED_BLOCK
) {
    override fun requirement(): ConiumArisingEventContext<Block, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.BLOCK,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.BLOCK_HIT_RESULT,
            ConiumEventArgTypes.ACTION_RESULT
        ) { identity: Any, world: World, player: PlayerEntity, blockPos: BlockPos, blockState: AbstractBlockState, hitResult: BlockHitResult, actionResult: ActionResult ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, player, blockPos, blockState, hitResult, actionResult)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Block>): ConiumUsedBlockEventMetadata {
        return ConiumUsedBlockEventMetadata(context)
    }
}
