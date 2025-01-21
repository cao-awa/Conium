package com.github.cao.awa.conium.item.event.tick.inventory

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.ConiumItemEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective5
import net.minecraft.entity.Entity
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ConiumItemInventoryTickEvent : ConiumItemEvent<ParameterSelective5<Boolean, World, Entity, ItemStack, Int, Boolean>>(ConiumEventType.ITEM_INVENTORY_TICK) {
    override fun requirement(): ConiumEventContext<out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.ENTITY,
            ConiumEventArgTypes.ITEM_STACK,
            ConiumEventArgTypes.SLOT_NUMBER,
            ConiumEventArgTypes.SELECT_STATUS,
        ).arise { identity: Any, world: World, entity: Entity, itemStack: ItemStack, slotNumber: Int, selectStatus: Boolean ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, entity, itemStack, slotNumber, selectStatus)
            }
        }
    }
}
