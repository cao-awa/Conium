package com.github.cao.awa.conium.item.event.use

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.ConiumItemEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.world.World

class ConiumItemUsedEvent : ConiumItemEvent<ConiumItemUsedEventMetadata, ParameterSelective5<Boolean, World, PlayerEntity, Hand, ItemStack, ActionResult>>(
    ConiumEventType.ITEM_USED
) {
    override fun requirement(): ConiumArisingEventContext<Item, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.ITEM,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.HAND,
            ConiumEventArgTypes.ITEM_STACK,
            ConiumEventArgTypes.ACTION_RESULT
        ) { identity: Item, world: World, user: PlayerEntity, hand: Hand, itemStack: ItemStack, actionResult: ActionResult ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, user, hand, itemStack, actionResult)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Item>): ConiumItemUsedEventMetadata {
        return ConiumItemUsedEventMetadata(context)
    }
}
