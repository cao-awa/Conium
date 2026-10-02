package com.github.cao.awa.conium.item.event.used.metadata
import com.github.cao.awa.conium.mapping.yarn.*

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionHand
import net.minecraft.world.level.Level

class ConiumItemUsedEventMetadata(val context: ConiumEventContext<Item>) : ConiumEventMetadata<Item, ConiumItemUsedEventMetadata>() {
    val world: World = this.context[ConiumEventArgTypes.WORLD]
    val user: Player = this.context[ConiumEventArgTypes.PLAYER]
    val hand: InteractionHand = this.context[ConiumEventArgTypes.HAND]
    val itemStack: ItemStack = this.context[ConiumEventArgTypes.ITEM_STACK]
    val actionResult: InteractionResult = this.context[ConiumEventArgTypes.ACTION_RESULT]
}