package com.github.cao.awa.conium.item.event.use

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.ConiumItemEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective2
import net.minecraft.item.ItemUsageContext
import net.minecraft.server.world.ServerWorld

class ConiumItemUseOnBlockEvent : ConiumItemEvent<ParameterSelective2<Boolean, ServerWorld, ItemUsageContext>>() {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.SERVER_WORLD,
            ConiumEventArgTypes.ITEM_USAGE_CONTEXT
        ).attach(
            forever(ConiumEventType.ITEM_USE_ON_BLOCK)
        ).arise { identity, world, context ->
            noFailure(identity) {
                it.arise(
                    world,
                    context
                )
            }
        }
    }
}
