package com.github.cao.awa.conium.entity.event.rest.waked

import com.github.cao.awa.conium.entity.event.rest.waked.metadata.ConiumEntityWakedUpEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective3
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumEntityWakedUpEvent : ConiumEvent<EntityType<*>, ConiumEntityWakedUpEventMetadata, ParameterSelective3<Boolean, World, LivingEntity, BlockPos>, ConiumInactiveEventType>(
    ConiumEventType.ENTITY_WAKED_UP,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<EntityType<*>, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
            ConiumEventArgTypes.ENTITY_TYPE,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.LIVING_ENTITY,
            ConiumEventArgTypes.BLOCK_POS
        ) { identity: Any, world: World, livingEntity: LivingEntity, sleepPos: BlockPos ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, livingEntity, sleepPos)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<EntityType<*>>): ConiumEntityWakedUpEventMetadata {
        return ConiumEntityWakedUpEventMetadata(context)
    }
}