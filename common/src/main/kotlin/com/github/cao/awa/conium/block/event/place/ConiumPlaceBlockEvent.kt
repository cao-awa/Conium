package com.github.cao.awa.conium.block.event.place

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.block.Block
import net.minecraft.item.ItemPlacementContext

class ConiumPlaceBlockEvent : ConiumEvent<Block, ConiumPlaceBlockEventMetadata, ParameterSelective1<Boolean, ItemPlacementContext>>(
    ConiumEventType.PLACE_BLOCK
) {
    override fun requirement(): ConiumArisingEventContext<Block, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.BLOCK,
            ConiumEventArgTypes.ITEM_PLACEMENT_CONTEXT
        ) { identity: Block, context: ItemPlacementContext ->
            noFailure(identity) {  parameterSelective ->
                parameterSelective(context)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Block>): ConiumPlaceBlockEventMetadata {
        return ConiumPlaceBlockEventMetadata(context)
    }
}
