package com.github.cao.awa.conium.block.event.place

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.block.AbstractBlock.AbstractBlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumPlacedBlockEvent : ConiumEvent<ParameterSelective5<Boolean, World, LivingEntity, BlockPos, AbstractBlockState, ItemStack>>() {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.LIVING_ENTITY,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.ITEM_STACK
        ).attach(
            forever(ConiumEventType.PLACED_BLOCK)
        ).arise { identity, world, entity, blockPos, blockState, itemStack ->
            noFailure(identity) {
                it.arise(world, entity, blockPos, blockState, itemStack)
            }
        }
    }
}