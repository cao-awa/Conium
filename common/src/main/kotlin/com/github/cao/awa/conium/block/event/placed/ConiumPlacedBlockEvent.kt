package com.github.cao.awa.conium.block.event.placed
import com.github.cao.awa.conium.mapping.yarn.*

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
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.Block
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level

class ConiumPlacedBlockEvent : ConiumEvent<Block, ConiumPlacedBlockEventMetadata, ParameterSelective5<Boolean, World, LivingEntity, BlockPos, BlockBehaviour.BlockStateBase, ItemStack>, ConiumInactiveEventType>(
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
            blockState: BlockBehaviour.BlockStateBase,
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