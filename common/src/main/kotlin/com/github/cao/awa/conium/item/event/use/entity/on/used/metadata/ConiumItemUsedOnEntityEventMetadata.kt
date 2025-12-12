package com.github.cao.awa.conium.item.event.use.entity.on.used.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.world.World

class ConiumItemUsedOnEntityEventMetadata(val context: ConiumEventContext<Item>) : ConiumEventMetadata<Item, ConiumItemUsedOnEntityEventMetadata>() {
    val world: World = this.context[ConiumEventArgTypes.WORLD]
    val user: PlayerEntity = this.context[ConiumEventArgTypes.PLAYER]
    val hand: Hand = this.context[ConiumEventArgTypes.HAND]
    val itemStack: ItemStack = this.context[ConiumEventArgTypes.ITEM_STACK]
    val actionResult: ActionResult = this.context[ConiumEventArgTypes.ACTION_RESULT]
}