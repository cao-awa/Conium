package com.github.cao.awa.conium.block.event.placed

import com.github.cao.awa.conium.block.event.placed.metadata.ConiumPlacedBlockEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumPlacedBlockEvent : ConiumEvent<Block, ConiumPlacedBlockEventMetadata, ParameterSelective5<Boolean, World, LivingEntity, BlockPos, AbstractBlock.AbstractBlockState, ItemStack>, ConiumInactiveEventType>(
    ConiumEventType.PLACED_BLOCK,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<Block, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.BLOCK,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.LIVING_ENTITY,
            ConiumEventArgTypes.BLOCK_POS,
            ConiumEventArgTypes.BLOCK_STATE,
            ConiumEventArgTypes.ITEM_STACK
        ) { identity: Block,
            world: World,
            entity: LivingEntity,
            blockPos: BlockPos,
            blockState: AbstractBlock.AbstractBlockState,
            itemStack: ItemStack ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, entity, blockPos, blockState, itemStack)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Block>): ConiumPlacedBlockEventMetadata {
        return ConiumPlacedBlockEventMetadata(context)
    }
}