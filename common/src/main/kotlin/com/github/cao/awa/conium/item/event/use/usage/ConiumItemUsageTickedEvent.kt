package com.github.cao.awa.conium.item.event.use.usage

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.ConiumItemEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ConiumItemUsageTickedEvent : ConiumItemEvent<ParameterSelective4<Boolean, World, LivingEntity, ItemStack, Int>, ConiumItemUsageTickedEventMetadata>(
    ConiumEventType.ITEM_USAGE_TICKED
) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requiresAny(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.LIVING_ENTITY,
            ConiumEventArgTypes.ITEM_STACK,
            ConiumEventArgTypes.REMAINING_USE_TICKS,
        ).arise { identity: Any, world: World, livingEntity: LivingEntity, itemStack: ItemStack, remainingUseTicks: Int ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, livingEntity, itemStack, remainingUseTicks)
            }
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumItemUsageTickedEventMetadata {
        return ConiumItemUsageTickedEventMetadata(context)
    }
}
