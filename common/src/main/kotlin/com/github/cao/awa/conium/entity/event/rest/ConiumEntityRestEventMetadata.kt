package com.github.cao.awa.conium.entity.event.rest

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_POS
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ENTITY
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.LIVING_ENTITY
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.WORLD
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

abstract class ConiumEntityRestEventMetadata(val context: ConiumEventContext) : ConiumEventMetadata() {
    val world: World = this.context[WORLD]
    val livingEntity: LivingEntity = this.context[LIVING_ENTITY]
    val sleepPos: BlockPos = this.context[BLOCK_POS]
}
