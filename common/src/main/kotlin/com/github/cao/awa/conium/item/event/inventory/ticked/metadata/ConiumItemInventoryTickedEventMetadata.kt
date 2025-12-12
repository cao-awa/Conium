package com.github.cao.awa.conium.item.event.inventory.ticked.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.entity.Entity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ConiumItemInventoryTickedEventMetadata(val context: ConiumEventContext<Item>) : ConiumEventMetadata<Item, ConiumItemInventoryTickedEventMetadata>() {
    val world: World = this.context[ConiumEventArgTypes.WORLD]
    val entity: Entity = this.context[ConiumEventArgTypes.ENTITY]
    val itemStack: ItemStack = this.context[ConiumEventArgTypes.ITEM_STACK]
    val slotNumber: Int = this.context[ConiumEventArgTypes.SLOT_NUMBER]
    val isSelected: Boolean = this.context[ConiumEventArgTypes.SELECT_STATUS]
}