package com.github.cao.awa.conium.entity.event.rest.sleep

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective3
import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ConiumEntityTrySleepEvent : ConiumEvent<ParameterSelective3<Boolean, World, LivingEntity, BlockPos>, ConiumEntityTrySleepEventMetadata>(
    ConiumEventType.ENTITY_TRY_SLEEP
) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requiresAny(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.LIVING_ENTITY,
            ConiumEventArgTypes.BLOCK_POS
        ).arise { identity: Any, world: World, livingEntity: LivingEntity, sleepPos: BlockPos ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, livingEntity, sleepPos)
            }
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumEntityTrySleepEventMetadata {
        return ConiumEntityTrySleepEventMetadata(context)
    }
}
