package com.github.cao.awa.conium.item.event.use

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.ConiumItemEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective3
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.world.World

class ConiumItemUsedEvent : ConiumItemEvent<ParameterSelective5<Boolean, World, PlayerEntity, Hand, ItemStack, ActionResult>, ConiumItemUsedEventMetadata>(
    ConiumEventType.ITEM_USED
) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.HAND,
            ConiumEventArgTypes.ITEM_STACK,
            ConiumEventArgTypes.ACTION_RESULT
        ).arise { identity: Any, world: World, user: PlayerEntity, hand: Hand, itemStack: ItemStack, actionResult: ActionResult ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, user, hand, itemStack, actionResult)
            }
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumItemUsedEventMetadata {
        return ConiumItemUsedEventMetadata(context)
    }
}
