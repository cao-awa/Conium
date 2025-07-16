package com.github.cao.awa.conium.entity.event.damage

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_POS
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_STATE
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.DAMAGE_AMOUNT
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.DAMAGE_SOURCE
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.FLUID_STATE
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.LIVING_ENTITY
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.SCHEDULED_TICK_VIEW
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.RANDOM
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.SERVER_WORLD
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.WORLD
import net.minecraft.block.BlockState
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.fluid.FluidState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.World

abstract class ConiumEntityDamagesEventMetadata(val context: ConiumEventContext<EntityType<*>>) : ConiumEventMetadata<EntityType<*>>() {
    val world: World = this.context[WORLD]
    val livingEntity: LivingEntity = this.context[LIVING_ENTITY]
    val damageSource: DamageSource = this.context[DAMAGE_SOURCE]
    val damageAmount: Float = this.context[DAMAGE_AMOUNT]
}
