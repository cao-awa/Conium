package com.github.cao.awa.conium.item.event.stack.clicked
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.item.event.stack.clicked.metadata.ConiumItemStackClickedEventMetadata
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.inventory.Slot
import net.minecraft.world.inventory.ClickAction as ClickType

class ConiumItemStackClickedEvent : ConiumEvent<Item, ConiumItemStackClickedEventMetadata, ParameterSelective4<Boolean, Player, ItemStack, ClickType, Slot>, ConiumInactiveEventType>(
    ConiumEventType.ITEM_STACK_CLICKED,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<Item, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.ITEM,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.ITEM_STACK,
            ConiumEventArgTypes.CLICK_TYPE,
            ConiumEventArgTypes.SLOT
        ) { identity: Item, player: Player, itemStack: ItemStack, clickType: ClickType, slot: Slot ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(player, itemStack, clickType, slot)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Item>): ConiumItemStackClickedEventMetadata {
        return ConiumItemStackClickedEventMetadata(context)
    }
}