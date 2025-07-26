package com.github.cao.awa.conium.entity.event.damage

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requires
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.requiresAny
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.world.World

class ConiumEntityDamageEvent : ConiumEvent<EntityType<*>, ConiumEntityDamageEventMetadata, ParameterSelective4<Boolean, World, LivingEntity, DamageSource, Float>>(
    ConiumEventType.ENTITY_DAMAGE,
    { ConiumEventType.ENTITY_DAMAGED }
) {
    override fun requirement(): ConiumArisingEventContext<EntityType<*>, out ParameterSelective> {
        return requires(
            ConiumEventArgTypes.ENTITY_TYPE,
            ConiumEventArgTypes.WORLD,
            ConiumEventArgTypes.LIVING_ENTITY,
            ConiumEventArgTypes.DAMAGE_SOURCE,
            ConiumEventArgTypes.DAMAGE_AMOUNT
        ) { identity: Any, world: World, livingEntity: LivingEntity, damageSource: DamageSource, amount: Float ->
            noFailure(identity) { parameterSelective ->
                parameterSelective(world, livingEntity, damageSource, amount)
            }
        }
    }

    override fun metadata(context: ConiumEventContext<EntityType<*>>): ConiumEntityDamageEventMetadata {
        return ConiumEntityDamageEventMetadata(context)
    }
}
