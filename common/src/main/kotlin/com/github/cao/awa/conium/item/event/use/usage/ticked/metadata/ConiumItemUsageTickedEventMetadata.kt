package com.github.cao.awa.conium.item.event.use.usage.ticked.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.script.index.common.REMAINING_USE_TICKS
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ConiumItemUsageTickedEventMetadata(val context: ConiumEventContext<Item>) : ConiumEventMetadata<Item, ConiumItemUsageTickedEventMetadata>() {
    val world: World = this.context[ConiumEventArgTypes.WORLD]
    val livingEntity: LivingEntity = this.context[ConiumEventArgTypes.LIVING_ENTITY]
    val itemStack: ItemStack = this.context[ConiumEventArgTypes.ITEM_STACK]
    val remainingUseTicks: Int = this.context[REMAINING_USE_TICKS]
}