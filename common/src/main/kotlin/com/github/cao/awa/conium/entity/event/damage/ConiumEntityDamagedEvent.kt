package com.github.cao.awa.conium.entity.event.damage

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.world.World

class ConiumEntityDamagedEvent : ConiumEvent<ParameterSelective4<Boolean, World, LivingEntity, DamageSource, Float>, ConiumEntityDamagedEventMetadata>(
    ConiumEventType.ENTITY_DAMAGED
) {
    override fun requirement(): ConiumArisingEventContext<out ParameterSelective> {
        return requiresAny(
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.LIVING_ENTITY,
            ConiumEventArgTypes.DAMAGE_SOURCE,
            ConiumEventArgTypes.DAMAGE_AMOUNT
        ).arise { identity: Any, world: World, livingEntity: LivingEntity, damageSource: DamageSource, amount: Float ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, livingEntity, damageSource, amount)
            }
        }
    }

    override fun metadata(context: ConiumEventContext): ConiumEntityDamagedEventMetadata {
        return ConiumEntityDamagedEventMetadata(context)
    }
}
