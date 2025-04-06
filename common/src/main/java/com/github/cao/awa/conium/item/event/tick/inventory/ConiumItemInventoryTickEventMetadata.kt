package com.github.cao.awa.conium.item.event.tick.inventory

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ENTITY
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ITEM_STACK
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.SELECT_STATUS
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.SLOT_NUMBER
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.WORLD
import net.minecraft.entity.Entity
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ConiumItemInventoryTickEventMetadata(val context: ConiumEventContext) : ConiumEventMetadata() {
    val world: World = this.context[WORLD]
    val entity: Entity = this.context[ENTITY]
    val itemStack: ItemStack = this.context[ITEM_STACK]
    val slotNumber: Int = this.context[SLOT_NUMBER]
    val isSelected: Boolean = this.context[SELECT_STATUS]
}
