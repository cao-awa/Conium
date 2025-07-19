package com.github.cao.awa.conium.item.event.use.block

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.ConiumItemEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective2
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.world.World

class ConiumItemUseOnBlockEvent : ConiumItemEvent<ConiumItemUseOnBlockEventMetadata, ParameterSelective2<Boolean, World, ItemUsageContext>>(
    ConiumEventType.ITEM_USE_ON_BLOCK
) {
    override fun requirement(): ConiumArisingEventContext<Item, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.ITEM,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.ITEM_USAGE_CONTEXT
        ) { identity: Item, world: World, context: ItemUsageContext ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, context)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Item>): ConiumItemUseOnBlockEventMetadata {
        return ConiumItemUseOnBlockEventMetadata(context)
    }
}
