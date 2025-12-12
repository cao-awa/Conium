package com.github.cao.awa.conium.item.event.use.entity.on.used

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.metadata.ConiumInactiveEventMetadata
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.item.event.use.entity.on.used.metadata.ConiumItemUsedOnEntityEventMetadata
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand

class ConiumItemUsedOnEntityEvent : ConiumEvent<Item, ConiumItemUsedOnEntityEventMetadata, ParameterSelective5<Boolean, PlayerEntity, LivingEntity, ItemStack, Hand, ActionResult>, ConiumInactiveEventType>(
    ConiumEventType.ITEM_USED_ON_ENTITY,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<Item, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.ITEM,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.LIVING_ENTITY,
            ConiumEventArgTypes.ITEM_STACK,
            ConiumEventArgTypes.HAND,
            ConiumEventArgTypes.ACTION_RESULT
        ) { identity: Item, player: PlayerEntity, livingEntity: LivingEntity, itemStack: ItemStack, hand: Hand, actionResult: ActionResult ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(player, livingEntity, itemStack, hand, actionResult)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Item>): ConiumItemUsedOnEntityEventMetadata {
        return ConiumItemUsedOnEntityEventMetadata(context)
    }
}