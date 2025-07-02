package com.github.cao.awa.conium.item.event.stack.click

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.CLICK_TYPE
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.CURSOR_STACK
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ITEM_STACK
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.PLAYER
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.SLOT
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.WORLD
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot
import net.minecraft.util.ClickType
import net.minecraft.world.World

class ConiumItemStackClickEventMetadata(val context: ConiumEventContext) : ConiumEventMetadata() {
    val player: PlayerEntity = this.context[PLAYER]
    val itemStack: ItemStack = this.context[ITEM_STACK]
    val cursorStack: ItemStack = this.context[CURSOR_STACK]
    val clickType: ClickType = this.context[CLICK_TYPE]
    val slot: Slot = this.context[SLOT]
}
