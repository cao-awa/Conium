package com.github.cao.awa.conium.item.event.stack.click

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.ConiumItemEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot
import net.minecraft.util.ClickType

class ConiumItemStackClickEvent : ConiumItemEvent<ParameterSelective5<Boolean, PlayerEntity, ItemStack, ItemStack, ClickType, Slot>>(ConiumEventType.ITEM_STACK_CLICK) {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.ITEM_STACK,
            ConiumEventArgTypes.CURSOR_STACK,
            ConiumEventArgTypes.CLICK_TYPE,
            ConiumEventArgTypes.SLOT,
        ).arise { identity: Any, player: PlayerEntity, itemStack: ItemStack, cursorStack: ItemStack, clickType: ClickType, slot: Slot ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(player, itemStack, cursorStack, clickType, slot)
            }
        }
    }
}
