package com.github.cao.awa.conium.craft.table.event.crafting

import com.github.cao.awa.conium.craft.table.event.crafted.type.ConiumCraftingTableCraftedEventType
import com.github.cao.awa.conium.craft.table.event.crafting.metadata.ConiumCraftingTableCraftingEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective2
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

class ConiumCraftingTableCraftingEvent : ConiumEvent<Item, ConiumCraftingTableCraftingEventMetadata, ParameterSelective2<Boolean, PlayerEntity, ItemStack>, ConiumCraftingTableCraftedEventType>(
    ConiumEventType.CRAFTING_TABLE_CRAFTING,
    { ConiumEventType.CRAFTING_TABLE_CRAFTED }
) {
    override fun requirement(): ConiumArisingEventContext<Item, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.ITEM,
            ConiumEventArgTypes.ITEM_STACK,
            ConiumEventArgTypes.PLAYER
        ) { identity: Item, stack: ItemStack, player: PlayerEntity ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(player, stack)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Item>): ConiumCraftingTableCraftingEventMetadata {
        return ConiumCraftingTableCraftingEventMetadata(context)
    }
}