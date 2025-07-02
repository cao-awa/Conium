package com.github.cao.awa.conium.item.event.use.usage

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ITEM_STACK
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.LIVING_ENTITY
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.WORLD
import com.github.cao.awa.conium.script.index.common.REMAINING_USE_TICKS
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ConiumItemUsageTickEventMetadata(val context: ConiumEventContext) : ConiumEventMetadata() {
    val world: World = this.context[WORLD]
    val livingEntity: LivingEntity = this.context[LIVING_ENTITY]
    val itemStack: ItemStack = this.context[ITEM_STACK]
    val remainingUseTicks: Int = this.context[REMAINING_USE_TICKS]
}
