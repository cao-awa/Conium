package com.github.cao.awa.conium.block.event.used

import com.github.cao.awa.conium.block.event.used.metadata.ConiumUsedBlockEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective6
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumUsedBlockEvent : ConiumEvent<Block, ConiumUsedBlockEventMetadata, ParameterSelective6<Boolean, World, PlayerEntity, BlockPos, AbstractBlock.AbstractBlockState, BlockHitResult, ActionResult>, ConiumInactiveEventType>(
    ConiumEventType.USED_BLOCK,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<Block, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.BLOCK,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.BLOCK_HIT_RESULT,
            ConiumEventArgTypes.ACTION_RESULT
        ) { identity: Any, world: World, player: PlayerEntity, blockPos: BlockPos, blockState: AbstractBlock.AbstractBlockState, hitResult: BlockHitResult, actionResult: ActionResult ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, player, blockPos, blockState, hitResult, actionResult)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Block>): ConiumUsedBlockEventMetadata {
        return ConiumUsedBlockEventMetadata(context)
    }
}