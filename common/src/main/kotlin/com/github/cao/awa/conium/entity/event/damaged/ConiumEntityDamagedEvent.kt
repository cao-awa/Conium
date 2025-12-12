package com.github.cao.awa.conium.entity.event.damaged

import com.github.cao.awa.conium.entity.event.damaged.metadata.ConiumEntityDamagedEventMetadata
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.inactive.event.type.ConiumInactiveEventType
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.parameter.ParameterSelective4
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.world.World

class ConiumEntityDamagedEvent : ConiumEvent<EntityType<*>, ConiumEntityDamagedEventMetadata, ParameterSelective4<Boolean, World, LivingEntity, DamageSource, Float>, ConiumInactiveEventType>(
    ConiumEventType.ENTITY_DAMAGED,
    { ConiumEventType.INACTIVE }
) {
    override fun requirement(): ConiumArisingEventContext<EntityType<*>, out ParameterSelective> {
        return ConiumEventContextBuilder.requires(
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

    override fun metadata(context: ConiumEventContext<EntityType<*>>): ConiumEntityDamagedEventMetadata {
        return ConiumEntityDamagedEventMetadata(context)
    }
}