package com.github.cao.awa.conium.item.event.use

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.item.event.use.metadata.ConiumItemUseEventMetadata
import com.github.cao.awa.conium.item.event.use.type.ConiumItemUseEventType
import com.github.cao.awa.conium.item.event.used.type.ConiumItemUsedEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.world.World

class ConiumItemUseEvent : ConiumEvent<Item, ConiumItemUseEventMetadata, ParameterSelective4<Boolean, World, PlayerEntity, Hand, ItemStack>, ConiumItemUsedEventType>(
    ConiumEventType.ITEM_USE,
    { ConiumEventType.ITEM_USED }
) {
    override fun requirement(): ConiumArisingEventContext<Item, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.ITEM,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.HAND,
            ConiumEventArgTypes.ITEM_STACK
        ) { identity: Item, world: World, user: PlayerEntity, hand: Hand, itemStack: ItemStack ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, user, hand, itemStack)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Item>): ConiumItemUseEventMetadata {
        return ConiumItemUseEventMetadata(context)
    }
}
