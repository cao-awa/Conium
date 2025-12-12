package com.github.cao.awa.conium.craft.table.event.crafted

import com.github.cao.awa.conium.craft.table.event.crafted.metadata.ConiumCraftingTableCraftedEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective2
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

class ConiumCraftingTableCraftedEvent : ConiumEvent<Item, ConiumCraftingTableCraftedEventMetadata, ParameterSelective2<Boolean, PlayerEntity, ItemStack>, ConiumInactiveEventType>(
    ConiumEventType.CRAFTING_TABLE_CRAFTED,
    { ConiumEventType.INACTIVE }
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

    override fun metadata(context: ConiumEventContext<Item>): ConiumCraftingTableCraftedEventMetadata {
        return ConiumCraftingTableCraftedEventMetadata(context)
    }
}