package com.github.cao.awa.conium.item.event.use.block

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.ConiumItemEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective3
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.ActionResult
import net.minecraft.world.World

class ConiumItemUsedOnBlockEvent : ConiumItemEvent<ParameterSelective3<Boolean, World, ItemUsageContext, ActionResult>, ConiumItemUsedOnBlockEventMetadata>(
    ConiumEventType.ITEM_USED_ON_BLOCK
) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requiresAny(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.ITEM_USAGE_CONTEXT,
            ConiumEventArgTypes.ACTION_RESULT
        ).arise { identity: Any, world: World, context: ItemUsageContext, result: ActionResult ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, context, result)
            }
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumItemUsedOnBlockEventMetadata {
        return ConiumItemUsedOnBlockEventMetadata(context)
    }
}
