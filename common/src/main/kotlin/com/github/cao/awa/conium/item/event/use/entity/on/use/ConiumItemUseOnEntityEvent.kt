package com.github.cao.awa.conium.item.event.use.entity.on.use

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.use.entity.on.used.metadata.ConiumItemUsedOnEntityEventMetadata
import com.github.cao.awa.conium.item.event.use.entity.on.use.metadata.ConiumItemUseOnEntityEventMetadata
import com.github.cao.awa.conium.item.event.use.entity.on.used.type.ConiumItemUsedOnEntityEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand

class ConiumItemUseOnEntityEvent : ConiumEvent<Item, ConiumItemUseOnEntityEventMetadata, ParameterSelective4<Boolean, PlayerEntity, LivingEntity, ItemStack, Hand>, ConiumItemUsedOnEntityEventType>(
    ConiumEventType.Companion.ITEM_USE_ON_ENTITY,
    { ConiumEventType.Companion.ITEM_USED_ON_ENTITY }
) {
    override fun requirement(): ConiumArisingEventContext<Item, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
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