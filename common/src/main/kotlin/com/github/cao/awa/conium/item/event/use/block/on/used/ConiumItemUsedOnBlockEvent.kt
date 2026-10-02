package com.github.cao.awa.conium.item.event.use.block.on.used
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.item.event.use.block.on.used.metadata.ConiumItemUsedOnBlockEventMetadata
import com.github.cao.awa.conium.item.event.use.block.on.used.type.ConiumItemUsedOnBlockEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective3
import net.minecraft.world.item.Item
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.InteractionResult
import net.minecraft.world.level.Level

class ConiumItemUsedOnBlockEvent : ConiumEvent<Item, ConiumItemUsedOnBlockEventMetadata, ParameterSelective3<Boolean, World, UseOnContext, InteractionResult>, ConiumInactiveEventType>(
    ConiumEventType.ITEM_USED_ON_BLOCK,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<Item, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.ITEM,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.ITEM_USAGE_CONTEXT,
            ConiumEventArgTypes.ACTION_RESULT
        ) { identity: Item, world: World, context: UseOnContext, result: InteractionResult ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, context, result)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Item>): ConiumItemUsedOnBlockEventMetadata {
        return ConiumItemUsedOnBlockEventMetadata(context)
    }
}