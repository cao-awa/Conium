package com.github.cao.awa.conium.entity.event.rest.sleep

import com.github.cao.awa.conium.entity.event.rest.ConiumEntityRestEventMetadata
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.BLOCK_POS
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.ENTITY
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.LIVING_ENTITY
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes.WORLD
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumEntityTrySleepEventMetadata(context: ConiumEventContext<EntityType<*>>) : ConiumEntityRestEventMetadata(context)
