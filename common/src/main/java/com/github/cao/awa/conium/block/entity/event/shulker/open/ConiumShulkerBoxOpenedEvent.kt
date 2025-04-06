package com.github.cao.awa.conium.block.entity.event.shulker.open

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.mixin.block.entity.shulker.ShulkerBoxBlockEntityMixin
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
 * @see ConiumShulkerBoxOpeningEvent
 * @see ShulkerBoxBlockEntityMixin
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
class ConiumShulkerBoxOpenedEvent : ConiumEvent<ParameterSelective5<Boolean, World, PlayerEntity, ShulkerBoxBlockEntity, AbstractBlockState, BlockPos>, ConiumShulkerOpenedEventMetadata>(
    ConiumEventType.SHULKER_BOX_OPENED
) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.BLOCK_ENTITY,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.BLOCK_POS
        ).arise { identity: Any, world: World, pos: PlayerEntity, blockEntity: BlockEntity, blockState: AbstractBlockState, blockPos: BlockPos ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, pos, blockEntity as ShulkerBoxBlockEntity, blockState, blockPos)
            }
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumShulkerOpenedEventMetadata {
        return ConiumShulkerOpenedEventMetadata(context)
    }
}
