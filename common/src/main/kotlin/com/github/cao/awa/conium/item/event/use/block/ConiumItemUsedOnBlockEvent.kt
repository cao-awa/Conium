package com.github.cao.awa.conium.item.event.use.block

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.ConiumItemEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective3
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.ActionResult
import net.minecraft.world.World

class ConiumItemUsedOnBlockEvent : ConiumItemEvent<ConiumItemUsedOnBlockEventMetadata, ParameterSelective3<Boolean, World, ItemUsageContext, ActionResult>>(
    ConiumEventType.ITEM_USED_ON_BLOCK
) {
    override fun requirement(): ConiumArisingEventContext<Item, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.ITEM,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.ITEM_USAGE_CONTEXT,
            ConiumEventArgTypes.ACTION_RESULT
        ) { identity: Item, world: World, context: ItemUsageContext, result: ActionResult ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, context, result)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Item>): ConiumItemUsedOnBlockEventMetadata {
        return ConiumItemUsedOnBlockEventMetadata(context)
    }
}
