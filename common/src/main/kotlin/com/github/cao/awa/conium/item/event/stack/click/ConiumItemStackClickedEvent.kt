package com.github.cao.awa.conium.item.event.stack.click

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.ConiumItemEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot
import net.minecraft.util.ClickType

class ConiumItemStackClickedEvent : ConiumItemEvent<ParameterSelective4<Boolean, PlayerEntity, ItemStack, ClickType, Slot>, ConiumItemStackClickedEventMetadata>(
    ConiumEventType.ITEM_STACK_CLICKED
) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.PLAYER,
            ConiumEventArgTypes.ITEM_STACK,
            ConiumEventArgTypes.CLICK_TYPE,
            ConiumEventArgTypes.SLOT
        ).arise { identity: Any, player: PlayerEntity, itemStack: ItemStack, clickType: ClickType, slot: Slot ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(player, itemStack, clickType, slot)
            }
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumItemStackClickedEventMetadata {
        return ConiumItemStackClickedEventMetadata(context)
    }
}
