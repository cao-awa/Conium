package com.github.cao.awa.conium.item.event.use.usage.ticked

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.item.event.use.usage.ticked.metadata.ConiumItemUsageTickedEventMetadata
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ConiumItemUsageTickedEvent : ConiumEvent<Item, ConiumItemUsageTickedEventMetadata, ParameterSelective4<Boolean, World, LivingEntity, ItemStack, Int>, ConiumInactiveEventType>(
    ConiumEventType.ITEM_USAGE_TICKED,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<Item, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
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

    override fun metadata(context: ConiumEventContext<Item>): ConiumItemUsageTickedEventMetadata {
        return ConiumItemUsageTickedEventMetadata(context)
    }
}