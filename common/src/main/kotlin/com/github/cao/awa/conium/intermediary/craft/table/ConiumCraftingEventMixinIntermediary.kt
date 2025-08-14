package com.github.cao.awa.conium.intermediary.craft.table

import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.intermediary.ConiumEventMixinIntermediary.fireEvent
import com.github.cao.awa.conium.intermediary.ConiumEventMixinIntermediary.fireEventCancelable
import com.github.cao.awa.conium.mapping.yarn.ItemStack
import net.minecraft.entity.player.PlayerEntity

object ConiumCraftingEventMixinIntermediary {
    @JvmStatic
    fun firePlayerCraftingItemEvent(player: PlayerEntity, itemStack: ItemStack): Boolean {
        return fireEventCancelable(
            ConiumEventType.CRAFTING_TABLE_CRAFTING,
            itemStack.item
        ) { craftingContext: ConiumArisingEventContext<*, *> ->
            craftingContext[ConiumEventArgTypes.PLAYER] = player
            craftingContext[ConiumEventArgTypes.ITEM_STACK] = itemStack
        }
    }

    @JvmStatic
    fun firePlayerCraftedItemEvent(player: PlayerEntity, itemStack: ItemStack) {
        fireEvent(
            ConiumEventType.CRAFTING_TABLE_CRAFTED,
            itemStack.item
        ) { craftedContext: ConiumArisingEventContext<*, *> ->
            // Fill extinguished context args.
            craftedContext[ConiumEventArgTypes.PLAYER] = player
            craftedContext[ConiumEventArgTypes.ITEM_STACK] = itemStack
        }
    }
}
