package com.github.cao.awa.conium.entity.event.rest.sleep.metadata

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumEntitySleepEventMetadata(val context: ConiumEventContext<EntityType<*>>) : ConiumEventMetadata<EntityType<*>, ConiumEntitySleepEventMetadata>() {
    val world: World = this.context[ConiumEventArgTypes.WORLD]
    val livingEntity: LivingEntity = this.context[ConiumEventArgTypes.LIVING_ENTITY]
    val sleepPos: BlockPos = this.context[ConiumEventArgTypes.BLOCK_POS]
}