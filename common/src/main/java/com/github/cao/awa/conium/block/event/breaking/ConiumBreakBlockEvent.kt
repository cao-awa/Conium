package com.github.cao.awa.conium.block.event.breaking

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective3
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumBreakBlockEvent : ConiumEvent<ParameterSelective3<Boolean, World, PlayerEntity, BlockPos>, ConiumBreakBlockEventMetadata>(
    ConiumEventType.BREAK_BLOCK
) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_POS
        ).arise { identity: Any, world: World, player: PlayerEntity, blockPos: BlockPos ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, player, blockPos)
            }
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumBreakBlockEventMetadata {
        return ConiumBreakBlockEventMetadata(context)
    }
}
