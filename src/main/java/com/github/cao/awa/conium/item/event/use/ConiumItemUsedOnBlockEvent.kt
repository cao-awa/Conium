package com.github.cao.awa.conium.item.event.use

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.ConiumItemEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective3
import net.minecraft.item.ItemUsageContext
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult

class ConiumItemUsedOnBlockEvent : ConiumItemEvent<ParameterSelective3<Boolean, ServerWorld, ItemUsageContext, ActionResult>>() {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.SERVER_WORLD,
            ConiumEventArgTypes.ITEM_USAGE_CONTEXT,
            ConiumEventArgTypes.ACTION_RESULT
        ).attach(
            forever(ConiumEventType.ITEM_USED_ON_BLOCK)
        ).arise { identity: Any, world: ServerWorld, context: ItemUsageContext, result: ActionResult ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, context, result)
            }
        }
    }
}
