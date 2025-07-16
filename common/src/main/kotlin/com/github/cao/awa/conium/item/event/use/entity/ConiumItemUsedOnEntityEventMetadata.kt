package com.github.cao.awa.conium.item.event.use.entity

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ACTION_RESULT
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_POS
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ENTITY
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.HAND
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ITEM_STACK
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.LIVING_ENTITY
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.PLAYER
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.WORLD
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumItemUsedOnEntityEventMetadata(val context: ConiumEventContext<Item>) : ConiumEventMetadata<Item>() {
    val world: World = this.context[WORLD]
    val user: PlayerEntity = this.context[PLAYER]
    val hand: Hand = this.context[HAND]
    val itemStack: ItemStack = this.context[ITEM_STACK]
    val actionResult: ActionResult = this.context[ACTION_RESULT]
}
