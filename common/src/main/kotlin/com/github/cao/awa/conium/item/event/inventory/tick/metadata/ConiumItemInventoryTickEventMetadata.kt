package com.github.cao.awa.conium.item.event.inventory.tick.metadata
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.world.entity.Entity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class ConiumItemInventoryTickEventMetadata(val context: ConiumEventContext<Item>) : ConiumEventMetadata<Item, ConiumItemInventoryTickEventMetadata>() {
    val world: World = this.context[ConiumEventArgTypes.WORLD]
    val entity: Entity = this.context[ConiumEventArgTypes.ENTITY]
    val itemStack: ItemStack = this.context[ConiumEventArgTypes.ITEM_STACK]
    val slotNumber: Int = this.context[ConiumEventArgTypes.SLOT_NUMBER]
    val isSelected: Boolean = this.context[ConiumEventArgTypes.SELECT_STATUS]
}