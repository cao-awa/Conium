package com.github.cao.awa.conium.item.event.use.entity.on.use
import com.github.cao.awa.conium.mapping.yarn.*

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
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.InteractionHand

class ConiumItemUseOnEntityEvent : ConiumEvent<Item, ConiumItemUseOnEntityEventMetadata, ParameterSelective4<Boolean, Player, LivingEntity, ItemStack, InteractionHand>, ConiumItemUsedOnEntityEventType>(
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
        ) { identity: Item, player: Player, livingEntity: LivingEntity, itemStack: ItemStack, hand: InteractionHand ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(player, livingEntity, itemStack, hand)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Item>): ConiumItemUseOnEntityEventMetadata {
        return ConiumItemUseOnEntityEventMetadata(context)
    }
}