package com.github.cao.awa.conium.block.event.place
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.block.event.place.metadata.ConiumPlaceBlockEventMetadata
import com.github.cao.awa.conium.block.event.placed.type.ConiumPlacedBlockEventType
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.world.level.block.Block
import net.minecraft.world.item.context.BlockPlaceContext

class ConiumPlaceBlockEvent : ConiumEvent<Block, ConiumPlaceBlockEventMetadata, ParameterSelective1<Boolean, BlockPlaceContext>, ConiumPlacedBlockEventType>(
    ConiumEventType.PLACE_BLOCK,
    { ConiumEventType.PLACED_BLOCK }
) {
    override fun requirement(): ConiumArisingEventContext<Block, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.BLOCK,
            ConiumEventArgTypes.ITEM_PLACEMENT_CONTEXT
        ) { identity: Block, context: BlockPlaceContext ->
            noFailure(identity) {  parameterSelective ->
                parameterSelective(context)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Block>): ConiumPlaceBlockEventMetadata {
        return ConiumPlaceBlockEventMetadata(context)
    }
}
