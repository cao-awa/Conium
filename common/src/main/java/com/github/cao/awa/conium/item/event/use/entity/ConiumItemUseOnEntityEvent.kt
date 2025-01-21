package com.github.cao.awa.conium.item.event.use.entity

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.ConiumItemEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand

class ConiumItemUseOnEntityEvent : ConiumItemEvent<ParameterSelective4<Boolean, PlayerEntity, LivingEntity, ItemStack, Hand>>(ConiumEventType.ITEM_USE_ON_ENTITY) {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.LIVING_ENTITY,
            ConiumEventArgTypes.ITEM_STACK,
            ConiumEventArgTypes.HAND,
        ).arise { identity: Any, player: PlayerEntity, livingEntity: LivingEntity, itemStack: ItemStack, hand: Hand ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(player, livingEntity, itemStack, hand)
            }
        }
    }
}
