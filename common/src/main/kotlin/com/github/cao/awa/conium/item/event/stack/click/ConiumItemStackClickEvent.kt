package com.github.cao.awa.conium.item.event.stack.click

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.stack.click.metadata.ConiumItemStackClickEventMetadata
import com.github.cao.awa.conium.item.event.stack.clicked.metadata.ConiumItemStackClickedEventMetadata
import com.github.cao.awa.conium.item.event.stack.clicked.type.ConiumItemStackClickedEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot
import net.minecraft.util.ClickType

class ConiumItemStackClickEvent : ConiumEvent<Item, ConiumItemStackClickEventMetadata, ParameterSelective5<Boolean, PlayerEntity, ItemStack, ItemStack, ClickType, Slot>, ConiumItemStackClickedEventType>(
    ConiumEventType.ITEM_STACK_CLICK,
    { ConiumEventType.ITEM_STACK_CLICKED }
) {
    override fun requirement(): ConiumArisingEventContext<Item, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.ITEM,
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.ITEM_STACK,
            ConiumEventArgTypes.CURSOR_STACK,
            ConiumEventArgTypes.CLICK_TYPE,
            ConiumEventArgTypes.SLOT,
        ) { identity: Item, player: PlayerEntity, itemStack: ItemStack, cursorStack: ItemStack, clickType: ClickType, slot: Slot ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(player, itemStack, cursorStack, clickType, slot)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Item>): ConiumItemStackClickEventMetadata {
        return ConiumItemStackClickEventMetadata(context)
    }
}
