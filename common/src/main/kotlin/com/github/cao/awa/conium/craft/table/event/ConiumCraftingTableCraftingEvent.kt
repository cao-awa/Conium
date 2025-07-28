package com.github.cao.awa.conium.craft.table.event

import com.github.cao.awa.conium.block.event.breaking.ConiumBrokenBlockEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective2
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.block.AbstractBlock.AbstractBlockState
import net.minecraft.block.Block
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumCraftingTableCraftingEvent : ConiumEvent<Item, ConiumCraftingTableCraftingEventMetadata, ParameterSelective2<Boolean, PlayerEntity, ItemStack>>(
    ConiumEventType.CRAFTING_TABLE_CRAFTING
) {
    override fun requirement(): ConiumArisingEventContext<Item, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.ITEM,
            ConiumEventArgTypes.ITEM_STACK,
            ConiumEventArgTypes.PLAYER
        ) { identity: Item, stack: ItemStack, player: PlayerEntity->
            noFailure(identity) { parameterSelective ->
                parameterSelective(player, stack)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<Item>): ConiumCraftingTableCraftingEventMetadata {
        return ConiumCraftingTableCraftingEventMetadata(context)
    }
}
