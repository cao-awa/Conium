package com.github.cao.awa.conium.entity.event.rest.wake

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

class ConiumEntityWakedUpEvent : ConiumEvent<ParameterSelective3<Boolean, World, LivingEntity, BlockPos>, ConiumEntityWakedUpEventMetadata>(
    ConiumEventType.ENTITY_WAKED_UP
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

    override fun metadata(context: ConiumEventContext): ConiumEntityWakedUpEventMetadata {
        return ConiumEntityWakedUpEventMetadata(context)
    }
}
