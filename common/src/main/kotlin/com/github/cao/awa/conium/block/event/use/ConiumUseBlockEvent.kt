package com.github.cao.awa.conium.block.event.use
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.block.event.use.metadata.ConiumUseBlockEventMetadata
import com.github.cao.awa.conium.block.event.used.type.ConiumUsedBlockEventType
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase
import net.minecraft.world.level.block.Block
import net.minecraft.world.entity.player.Player
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level

class ConiumUseBlockEvent : ConiumEvent<Block, ConiumUseBlockEventMetadata, ParameterSelective5<Boolean, World, Player, BlockPos, AbstractBlockState, BlockHitResult>, ConiumUsedBlockEventType>(
    ConiumEventType.USE_BLOCK,
    { ConiumEventType.USED_BLOCK }
) {
    override fun requirement(): ConiumArisingEventContext<Block, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.BLOCK,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.BLOCK_HIT_RESULT
        ) { identity: Any, world: World, player: Player, blockPos: BlockPos, blockState: AbstractBlockState, hitResult: BlockHitResult ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, player, blockPos, blockState, hitResult)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Block>): ConiumUseBlockEventMetadata {
        return ConiumUseBlockEventMetadata(context)
    }
}
