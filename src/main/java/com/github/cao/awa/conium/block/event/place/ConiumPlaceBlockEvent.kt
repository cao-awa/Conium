package com.github.cao.awa.conium.block.event.place

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective1
import net.minecraft.item.ItemPlacementContext

class ConiumPlaceBlockEvent : ConiumEvent<ParameterSelective1<Boolean, ItemPlacementContext>>() {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.ITEM_PLACEMENT_CONTEXT
        ).attach(
            forever(ConiumEventType.PLACE_BLOCK)
        ).arise { identity, context ->
            noFailure(identity) {
                it.arise(context)
            }
        }
    }
}
