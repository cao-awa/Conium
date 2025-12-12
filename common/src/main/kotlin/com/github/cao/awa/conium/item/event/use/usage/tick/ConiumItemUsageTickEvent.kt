package com.github.cao.awa.conium.item.event.use.usage.tick

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.use.usage.ticked.metadata.ConiumItemUsageTickedEventMetadata
import com.github.cao.awa.conium.item.event.use.usage.tick.metadata.ConiumItemUsageTickEventMetadata
import com.github.cao.awa.conium.item.event.use.usage.ticked.type.ConiumItemUsageTickedEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ConiumItemUsageTickEvent : ConiumEvent<Item, ConiumItemUsageTickEventMetadata, ParameterSelective4<Boolean, World, LivingEntity, ItemStack, Int>, ConiumItemUsageTickedEventType>(
    ConiumEventType.Companion.ITEM_USAGE_TICK,
    { ConiumEventType.Companion.ITEM_USAGE_TICKED }
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

    override fun metadata(context: ConiumEventContext<Item>): ConiumItemUsageTickEventMetadata {
        return ConiumItemUsageTickEventMetadata(context)
    }
}