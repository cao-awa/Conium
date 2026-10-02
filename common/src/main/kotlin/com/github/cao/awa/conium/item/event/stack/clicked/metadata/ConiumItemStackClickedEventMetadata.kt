package com.github.cao.awa.conium.item.event.stack.clicked.metadata
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.inventory.Slot
import net.minecraft.world.inventory.ClickAction as ClickType

class ConiumItemStackClickedEventMetadata(val context: ConiumEventContext<Item>) : ConiumEventMetadata<Item, ConiumItemStackClickedEventMetadata>() {
    val player: Player = this.context[ConiumEventArgTypes.PLAYER]
    val itemStack: ItemStack = this.context[ConiumEventArgTypes.ITEM_STACK]
    val clickType: ClickType = this.context[ConiumEventArgTypes.CLICK_TYPE]
    val slot: Slot = this.context[ConiumEventArgTypes.SLOT]
}