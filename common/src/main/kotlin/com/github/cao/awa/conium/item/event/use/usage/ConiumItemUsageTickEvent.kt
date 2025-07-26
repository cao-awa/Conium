package com.github.cao.awa.conium.item.event.use.usage

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.ConiumItemEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ConiumItemUsageTickEvent : ConiumItemEvent<ConiumItemUsageTickEventMetadata, ParameterSelective4<Boolean, World, LivingEntity, ItemStack, Int>>(
    ConiumEventType.ITEM_USAGE_TICK,
    { ConiumEventType.ITEM_USAGE_TICKED }
) {
    override fun requirement(): ConiumArisingEventContext<Item, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.ITEM,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.LIVING_ENTITY,
            ConiumEventArgTypes.ITEM_STACK,
            ConiumEventArgTypes.REMAINING_USE_TICKS,
        ) { identity: Item, world: World, livingEntity: LivingEntity, itemStack: ItemStack, remainingUseTicks: Int ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, livingEntity, itemStack, remainingUseTicks)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Item>): ConiumItemUsageTickEventMetadata {
        return ConiumItemUsageTickEventMetadata(context)
    }
}
