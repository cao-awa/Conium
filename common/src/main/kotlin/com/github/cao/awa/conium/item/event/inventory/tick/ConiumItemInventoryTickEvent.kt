package com.github.cao.awa.conium.item.event.inventory.tick

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.inventory.tick.metadata.ConiumItemInventoryTickEventMetadata
import com.github.cao.awa.conium.item.event.inventory.ticked.type.ConiumItemInventoryTickedEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.entity.Entity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ConiumItemInventoryTickEvent : ConiumEvent<Item, ConiumItemInventoryTickEventMetadata, ParameterSelective5<Boolean, World, Entity, ItemStack, Int, Boolean>, ConiumItemInventoryTickedEventType>(
    ConiumEventType.Companion.ITEM_INVENTORY_TICK,
    { ConiumEventType.Companion.ITEM_INVENTORY_TICKED }
) {
    override fun requirement(): ConiumArisingEventContext<Item, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.ITEM,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.ENTITY,
            ConiumEventArgTypes.ITEM_STACK,
            ConiumEventArgTypes.SLOT_NUMBER,
            ConiumEventArgTypes.SELECT_STATUS,
        ) { identity: Item, world: World, entity: Entity, itemStack: ItemStack, slotNumber: Int, selectStatus: Boolean ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, entity, itemStack, slotNumber, selectStatus)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Item>): ConiumItemInventoryTickEventMetadata {
        return ConiumItemInventoryTickEventMetadata(context)
    }
}