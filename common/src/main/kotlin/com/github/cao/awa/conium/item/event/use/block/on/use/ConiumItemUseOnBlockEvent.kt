package com.github.cao.awa.conium.item.event.use.block.on.use

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.use.block.on.used.ConiumItemUsedOnBlockEvent
import com.github.cao.awa.conium.item.event.use.block.on.use.metadata.ConiumItemUseOnBlockEventMetadata
import com.github.cao.awa.conium.item.event.use.block.on.used.type.ConiumItemUsedOnBlockEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective2
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.world.World

class ConiumItemUseOnBlockEvent : ConiumEvent<Item, ConiumItemUseOnBlockEventMetadata, ParameterSelective2<Boolean, World, ItemUsageContext>, ConiumItemUsedOnBlockEventType>(
    ConiumEventType.Companion.ITEM_USE_ON_BLOCK,
    { ConiumEventType.Companion.ITEM_USED_ON_BLOCK }
) {
    override fun requirement(): ConiumArisingEventContext<Item, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
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