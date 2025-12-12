package com.github.cao.awa.conium.entity.event.rest.sleep

import com.github.cao.awa.conium.entity.event.rest.sleep.metadata.ConiumEntitySleepEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
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

class ConiumEntitySleepEvent : ConiumEvent<EntityType<*>, ConiumEntitySleepEventMetadata, ParameterSelective3<Boolean, World, LivingEntity, BlockPos>, ConiumInactiveEventType>(
    ConiumEventType.ENTITY_SLEEP,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<EntityType<*>, out ParameterSelective> {
        return requires(
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

    override fun metadata(context: ConiumEventContext<EntityType<*>>): ConiumEntitySleepEventMetadata {
        return ConiumEntitySleepEventMetadata(context)
    }
}
