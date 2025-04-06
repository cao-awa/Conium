package com.github.cao.awa.conium.item.event.use.block

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.ConiumItemEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective2
import net.minecraft.item.ItemUsageContext
import net.minecraft.world.World

class ConiumItemUseOnBlockEvent : ConiumItemEvent<ParameterSelective2<Boolean, World, ItemUsageContext>, ConiumItemUseOnBlockEventMetadata>(
    ConiumEventType.ITEM_USE_ON_BLOCK
) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.ITEM_USAGE_CONTEXT
        ).arise { identity: Any, world: World, context: ItemUsageContext ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, context)
            }
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumItemUseOnBlockEventMetadata {
        return ConiumItemUseOnBlockEventMetadata(context)
    }
}
