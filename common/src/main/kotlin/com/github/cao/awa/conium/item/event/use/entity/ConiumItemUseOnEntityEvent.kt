package com.github.cao.awa.conium.item.event.use.entity

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
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand

class ConiumItemUseOnEntityEvent : ConiumItemEvent<ConiumItemUseOnEntityEventMetadata, ParameterSelective4<Boolean, PlayerEntity, LivingEntity, ItemStack, Hand>>(
    ConiumEventType.ITEM_USE_ON_ENTITY
) {
    override fun requirement(): ConiumArisingEventContext<Item, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.ITEM,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.LIVING_ENTITY,
            ConiumEventArgTypes.ITEM_STACK,
            ConiumEventArgTypes.HAND,
        ) { identity: Item, player: PlayerEntity, livingEntity: LivingEntity, itemStack: ItemStack, hand: Hand ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(player, livingEntity, itemStack, hand)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Item>): ConiumItemUseOnEntityEventMetadata {
        return ConiumItemUseOnEntityEventMetadata(context)
    }
}
