package com.github.cao.awa.conium.entity.event.fire

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_POS
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_STATE
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.DAMAGE_AMOUNT
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.DAMAGE_SOURCE
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ENTITY
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.FLUID_STATE
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.INT
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.LIVING_ENTITY
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.SCHEDULED_TICK_VIEW
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.RANDOM
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.SERVER_WORLD
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.WORLD
import com.github.cao.awa.conium.mapping.yarn.DamageSource
import com.github.cao.awa.conium.mapping.yarn.ScheduledTickView
import com.github.cao.awa.conium.mapping.yarn.ServerWorld
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.fluid.FluidState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.World

class ConiumEntityOnFireEventMetadata(val context: ConiumEventContext) : ConiumEventMetadata() {
    val entity: Entity = this.context[ENTITY]
    val fireTicks: Int = this.context[INT]
}
