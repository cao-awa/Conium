package com.github.cao.awa.conium.block.entity.event.shulker.close

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.block.AbstractBlock.AbstractBlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.ShulkerBoxBlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 *
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
class ConiumShulkerBoxClosingEvent : ConiumEvent<ParameterSelective5<Boolean, World, PlayerEntity, ShulkerBoxBlockEntity, AbstractBlockState, BlockPos>>() {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_ENTITY,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.BLOCK_POS
        ).attach(
            forever(ConiumEventType.SHULKER_BOX_CLOSING)
        ).arise { identity, world: World, pos: PlayerEntity, blockEntity: BlockEntity, blockState: AbstractBlockState, blockPos: BlockPos ->
            noFailure(identity) {
                it.arise(world, pos, blockEntity as ShulkerBoxBlockEntity, blockState, blockPos)
            }
        }
    }
}
