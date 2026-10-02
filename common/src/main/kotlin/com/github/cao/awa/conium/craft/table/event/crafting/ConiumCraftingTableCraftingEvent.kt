package com.github.cao.awa.conium.craft.table.event.crafting
import com.github.cao.awa.conium.mapping.yarn.*

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
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class ConiumCraftingTableCraftingEvent : ConiumEvent<Item, ConiumCraftingTableCraftingEventMetadata, ParameterSelective2<Boolean, Player, ItemStack>, ConiumCraftingTableCraftedEventType>(
    ConiumEventType.CRAFTING_TABLE_CRAFTING,
    { ConiumEventType.CRAFTING_TABLE_CRAFTED }
) {
    override fun requirement(): ConiumArisingEventContext<Item, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.ITEM,
            ConiumEventArgTypes.ITEM_STACK,
            ConiumEventArgTypes.PLAYER
        ) { identity: Item, stack: ItemStack, player: Player ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(player, stack)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Item>): ConiumCraftingTableCraftingEventMetadata {
        return ConiumCraftingTableCraftingEventMetadata(context)
    }
}