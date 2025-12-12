package com.github.cao.awa.conium.item.event.stack.clicked.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot
import net.minecraft.util.ClickType

class ConiumItemStackClickedEventMetadata(val context: ConiumEventContext<Item>) : ConiumEventMetadata<Item, ConiumItemStackClickedEventMetadata>() {
    val player: PlayerEntity = this.context[ConiumEventArgTypes.PLAYER]
    val itemStack: ItemStack = this.context[ConiumEventArgTypes.ITEM_STACK]
    val clickType: ClickType = this.context[ConiumEventArgTypes.CLICK_TYPE]
    val slot: Slot = this.context[ConiumEventArgTypes.SLOT]
}