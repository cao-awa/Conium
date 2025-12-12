package com.github.cao.awa.conium.item.event.inventory.ticked

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.item.event.inventory.ticked.metadata.ConiumItemInventoryTickedEventMetadata
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.entity.Entity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ConiumItemInventoryTickedEvent : ConiumEvent<Item, ConiumItemInventoryTickedEventMetadata, ParameterSelective5<Boolean, World, Entity, ItemStack, Int, Boolean>, ConiumInactiveEventType>(
    ConiumEventType.ITEM_INVENTORY_TICKED,
    { ConiumEventType.INACTIVE }
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

    override fun metadata(context: ConiumEventContext<Item>): ConiumItemInventoryTickedEventMetadata {
        return ConiumItemInventoryTickedEventMetadata(context)
    }
}