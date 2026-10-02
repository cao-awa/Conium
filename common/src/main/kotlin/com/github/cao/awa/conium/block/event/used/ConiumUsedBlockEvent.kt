package com.github.cao.awa.conium.block.event.used
import com.github.cao.awa.conium.mapping.yarn.*

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
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.Block
import net.minecraft.world.entity.player.Player
import net.minecraft.world.InteractionResult
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level

class ConiumUsedBlockEvent : ConiumEvent<Block, ConiumUsedBlockEventMetadata, ParameterSelective6<Boolean, World, Player, BlockPos, BlockBehaviour.BlockStateBase, BlockHitResult, InteractionResult>, ConiumInactiveEventType>(
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
        ) { identity: Any, world: World, player: Player, blockPos: BlockPos, blockState: BlockBehaviour.BlockStateBase, hitResult: BlockHitResult, actionResult: InteractionResult ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, player, blockPos, blockState, hitResult, actionResult)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Block>): ConiumUsedBlockEventMetadata {
        return ConiumUsedBlockEventMetadata(context)
    }
}