package com.github.cao.awa.conium.entity.event.die

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective3
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.world.World

class ConiumEntityDieEvent : ConiumEvent<ParameterSelective3<Boolean, World, LivingEntity, DamageSource>, ConiumEntityDieEventMetadata>(
    ConiumEventType.ENTITY_DIE
) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requiresAny(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.LIVING_ENTITY,
            ConiumEventArgTypes.DAMAGE_SOURCE
        ).arise { identity: Any, world: World, livingEntity: LivingEntity, damageSource: DamageSource ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, livingEntity, damageSource)
            }
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumEntityDieEventMetadata {
        return ConiumEntityDieEventMetadata(context)
    }
}
