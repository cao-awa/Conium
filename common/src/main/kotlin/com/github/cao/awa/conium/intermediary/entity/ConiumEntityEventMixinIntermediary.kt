package com.github.cao.awa.conium.intermediary.entity

import com.github.cao.awa.conium.entity.event.damage.metadata.ConiumEntityDamageEventMetadata
import com.github.cao.awa.conium.entity.event.damaged.metadata.ConiumEntityDamagedEventMetadata
import com.github.cao.awa.conium.entity.event.dead.metadata.ConiumEntityDeadEventMetadata
import com.github.cao.awa.conium.entity.event.die.metadata.ConiumEntityDieEventMetadata
import com.github.cao.awa.conium.entity.event.rest.sleep.metadata.ConiumEntitySleepEventMetadata
import com.github.cao.awa.conium.entity.event.rest.sleep.metadata.ConiumEntityTrySleepEventMetadata
import com.github.cao.awa.conium.entity.event.rest.wake.metadata.ConiumEntityWakeUpEventMetadata
import com.github.cao.awa.conium.entity.event.rest.waked.metadata.ConiumEntityWakedUpEventMetadata
import com.github.cao.awa.conium.entity.event.sprint.metadata.ConiumEntitySprintEventMetadata
import com.github.cao.awa.conium.entity.event.sprinting.metadata.ConiumEntitySprintingEventMetadata
import com.github.cao.awa.conium.entity.event.sprinting.stop.metadata.ConiumEntityStopSprintEventMetadata
import com.github.cao.awa.conium.entity.event.tick.ConiumEntityTickEvent
import com.github.cao.awa.conium.entity.event.ticked.ConiumEntityTickedEvent
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.intermediary.ConiumEventMixinIntermediary
import com.github.cao.awa.conium.intermediary.ConiumEventMixinIntermediary.fireCascadedEvent
import com.github.cao.awa.conium.intermediary.ConiumEventMixinIntermediary.fireEvent
import com.github.cao.awa.conium.intermediary.ConiumEventMixinIntermediary.fireEventCancelable
import com.github.cao.awa.conium.mixin.entity.EntityMixin
import com.github.cao.awa.conium.mixin.entity.living.LivingEntityMixin
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.fluid.Fluid
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos

/**
 * Conium entity event intermediary triggers.
 *
 * @see Entity
 * @see LivingEntity
 * @see EntityType
 * @see EntityMixin
 * @see LivingEntityMixin
 * @see ConiumEventMixinIntermediary
 * @see ConiumEvent
 * @see ConiumEventType.ENTITY_ON_FIRE
 * @see ConiumEventType.ENTITY_EXTINGUISH_FIRE
 * @see ConiumEventType.ENTITY_EXTINGUISHED_FIRE
 *
 * @author cao_awa
 *
 * @since 1.0.0
 */
object ConiumEntityEventMixinIntermediary {
    /**
     * Trigger the entity damage events when entity damaging or damaged.
     *
     * @see LivingEntity
     * @see LivingEntityMixin
     * @see LivingEntity.onDamaged
     * @see DamageSource
     * @see ConiumEventType.ENTITY_DAMAGE
     * @see ConiumEventType.ENTITY_DAMAGED
     *
     * @param eventType the damage event type
     * @param entity the entity that damaging or damaged
     * @param damageSource the damage source
     * @param amount the damage amount
     *
     * @return flag that noted should do mixin cancel
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun fireEntityDamageEvent(eventType: ConiumEventType<EntityType<*>, ConiumEntityDamageEventMetadata, *, *>, entity: LivingEntity, damageSource: DamageSource, amount: Float): Boolean {
        return fireEventCancelable(
            eventType,
            entity.type
        ) { context: ConiumArisingEventContext<*, *> ->
            // Fill the context args.
            context[ConiumEventArgTypes.WORLD] = entity.entityWorld
            context[ConiumEventArgTypes.DAMAGE_SOURCE] = damageSource
            context[ConiumEventArgTypes.LIVING_ENTITY] = entity
            context[ConiumEventArgTypes.DAMAGE_AMOUNT] = amount
        }
    }

    /**
     * Trigger the entity damage events when entity damaging or damaged.
     *
     * @see LivingEntity
     * @see LivingEntityMixin
     * @see LivingEntity.onDamaged
     * @see DamageSource
     * @see ConiumEventType.ENTITY_DAMAGE
     * @see ConiumEventType.ENTITY_DAMAGED
     *
     * @param eventType the damage event type
     * @param entity the entity that damaging or damaged
     * @param damageSource the damage source
     * @param amount the damage amount
     *
     * @return flag that noted should do mixin cancel
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun fireEntityDamagedEvent(eventType: ConiumEventType<EntityType<*>, ConiumEntityDamagedEventMetadata, *, *>, entity: LivingEntity, damageSource: DamageSource, amount: Float): Boolean {
        return fireEventCancelable(
            eventType,
            entity.type
        ) { context: ConiumArisingEventContext<*, *> ->
            // Fill the context args.
            context[ConiumEventArgTypes.WORLD] = entity.entityWorld
            context[ConiumEventArgTypes.DAMAGE_SOURCE] = damageSource
            context[ConiumEventArgTypes.LIVING_ENTITY] = entity
            context[ConiumEventArgTypes.DAMAGE_AMOUNT] = amount
        }
    }

    /**
     * Trigger the entity dying.
     *
     * @see LivingEntity
     * @see LivingEntityMixin
     * @see LivingEntity.onDeath
     * @see DamageSource
     * @see ConiumEventType.ENTITY_DIE
     * @see ConiumEventType.ENTITY_DEAD
     *
     * @param eventType the dye event type
     * @param entity the entity that dying or dead
     * @param damageSource the damage source
     *
     * @return flag that noted should do mixin cancel
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun fireEntityDieEvent(eventType: ConiumEventType<EntityType<*>, ConiumEntityDieEventMetadata, *, *>, entity: LivingEntity, damageSource: DamageSource): Boolean {
        return fireEventCancelable(
            eventType,
            entity.type
        ) { context: ConiumArisingEventContext<*, *> ->
            // Fill the context args.
            context[ConiumEventArgTypes.DAMAGE_SOURCE] = damageSource
            context[ConiumEventArgTypes.LIVING_ENTITY] = entity
        }
    }

    /**
     * Trigger the entity dead.
     *
     * @see LivingEntity
     * @see LivingEntityMixin
     * @see LivingEntity.onDeath
     * @see DamageSource
     * @see ConiumEventType.ENTITY_DIE
     * @see ConiumEventType.ENTITY_DEAD
     *
     * @param eventType the dye event type
     * @param entity the entity that dying or dead
     * @param damageSource the damage source
     *
     * @return flag that noted should do mixin cancel
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun fireEntityDeadEvent(eventType: ConiumEventType<EntityType<*>, ConiumEntityDeadEventMetadata, *, *>, entity: LivingEntity, damageSource: DamageSource): Boolean {
        return fireEventCancelable(
            eventType,
            entity.type
        ) { context: ConiumArisingEventContext<*, *> ->
            // Fill the context args.
            context[ConiumEventArgTypes.DAMAGE_SOURCE] = damageSource
            context[ConiumEventArgTypes.LIVING_ENTITY] = entity
        }
    }

    /**
     * Trigger the entity try to sleep.
     *
     * @see LivingEntity
     * @see LivingEntityMixin
     * @see LivingEntity.onDeath
     * @see DamageSource
     * @see ConiumEventType.ENTITY_DIE
     * @see ConiumEventType.ENTITY_DEAD
     *
     * @param eventType the dye event type
     * @param entity the entity that dying or dead
     * @param sleepPos the position that entity sleeping at
     *
     * @return flag that noted should do mixin cancel
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun fireEntityTrySleepEvent(eventType: ConiumEventType<EntityType<*>, ConiumEntityTrySleepEventMetadata, *, *>, entity: LivingEntity, sleepPos: BlockPos?): Boolean {
        // Let this event failure directly when sleeping pos is null,
        // because the 'sleep' call will input a position to try sleep,
        // and 'wakeUp' call input a null when mean this entity are not sleeping currently.
        if (sleepPos == null) {
            return true
        }

        // If position present, trigger the event.
        return fireEventCancelable(
            eventType,
            entity.type
        ) { context: ConiumArisingEventContext<*, *> ->
            // Fill the context args.
            context[ConiumEventArgTypes.LIVING_ENTITY] = entity
            context[ConiumEventArgTypes.BLOCK_POS] = sleepPos
            context[ConiumEventArgTypes.WORLD] = entity.entityWorld
        }
    }

    /**
     * Trigger the entity to sleep.
     *
     * @see LivingEntity
     * @see LivingEntityMixin
     * @see LivingEntity.onDeath
     * @see DamageSource
     * @see ConiumEventType.ENTITY_DIE
     * @see ConiumEventType.ENTITY_DEAD
     *
     * @param eventType the dye event type
     * @param entity the entity that dying or dead
     * @param sleepPos the position that entity sleeping at
     *
     * @return flag that noted should do mixin cancel
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun fireEntitySleepEvent(eventType: ConiumEventType<EntityType<*>, ConiumEntitySleepEventMetadata, *, *>, entity: LivingEntity, sleepPos: BlockPos?): Boolean {
        // Let this event failure directly when sleeping pos is null,
        // because the 'sleep' call will input a position to try sleep,
        // and 'wakeUp' call input a null when mean this entity are not sleeping currently.
        if (sleepPos == null) {
            return true
        }

        // If position present, trigger the event.
        return fireEventCancelable(
            eventType,
            entity.type
        ) { context: ConiumArisingEventContext<*, *> ->
            // Fill the context args.
            context[ConiumEventArgTypes.LIVING_ENTITY] = entity
            context[ConiumEventArgTypes.BLOCK_POS] = sleepPos
            context[ConiumEventArgTypes.WORLD] = entity.entityWorld
        }
    }

    /**
     * Trigger the entity try to wakeup.
     *
     * @see LivingEntity
     * @see LivingEntityMixin
     * @see LivingEntity.onDeath
     * @see DamageSource
     * @see ConiumEventType.ENTITY_DIE
     * @see ConiumEventType.ENTITY_DEAD
     *
     * @param eventType the dye event type
     * @param entity the entity that dying or dead
     * @param sleepPos the position that entity sleeping at
     *
     * @return flag that noted should do mixin cancel
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun fireEntityWakeupEvent(eventType: ConiumEventType<EntityType<*>, ConiumEntityWakeUpEventMetadata, *, *>, entity: LivingEntity, sleepPos: BlockPos?): Boolean {
        // Let this event failure directly when sleeping pos is null,
        // because the 'sleep' call will input a position to try sleep,
        // and 'wakeUp' call input a null when mean this entity are not sleeping currently.
        if (sleepPos == null) {
            return true
        }

        // If position present, trigger the event.
        return fireEventCancelable(
            eventType,
            entity.type
        ) { context: ConiumArisingEventContext<*, *> ->
            // Fill the context args.
            context[ConiumEventArgTypes.LIVING_ENTITY] = entity
            context[ConiumEventArgTypes.BLOCK_POS] = sleepPos
            context[ConiumEventArgTypes.WORLD] = entity.entityWorld
        }
    }

    /**
     * Trigger the entity waked up.
     *
     * @see LivingEntity
     * @see LivingEntityMixin
     * @see LivingEntity.onDeath
     * @see DamageSource
     * @see ConiumEventType.ENTITY_DIE
     * @see ConiumEventType.ENTITY_DEAD
     *
     * @param eventType the dye event type
     * @param entity the entity that dying or dead
     * @param sleepPos the position that entity sleeping at
     *
     * @return flag that noted should do mixin cancel
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun fireEntityWakedUpEvent(eventType: ConiumEventType<EntityType<*>, ConiumEntityWakedUpEventMetadata, *, *>, entity: LivingEntity, sleepPos: BlockPos?): Boolean {
        // Let this event failure directly when sleeping pos is null,
        // because the 'sleep' call will input a position to try sleep,
        // and 'wakeUp' call input a null when mean this entity are not sleeping currently.
        if (sleepPos == null) {
            return true
        }

        // If position present, trigger the event.
        return fireEventCancelable(
            eventType,
            entity.type
        ) { context: ConiumArisingEventContext<*, *> ->
            // Fill the context args.
            context[ConiumEventArgTypes.LIVING_ENTITY] = entity
            context[ConiumEventArgTypes.BLOCK_POS] = sleepPos
            context[ConiumEventArgTypes.WORLD] = entity.entityWorld
        }
    }

    /**
     * Trigger the entity on fire event when entity ticking during fire ticks has least 1.
     *
     * @see EntityMixin
     * @see EntityMixin.onExtinguish
     * @see Entity.baseTick
     * @see Entity.setFireTicks
     * @see ConiumEventType.ENTITY_ON_FIRE
     *
     * @param entity the entity that extinguishing
     *
     * @return flag that noted should do mixin cancel
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun fireOnFireEvent(entity: Entity) {
        // Only trigger event when fire ticks left at least 1.
        if (entity.fireTicks > 0) {
            // This event cannot cancel because it is not the fire tick event, on fire event just a notice event.
            fireEvent(
                ConiumEventType.ENTITY_ON_FIRE,
                entity.type
            ) { context: ConiumArisingEventContext<*, *> ->
                // Fill the context args.
                context[ConiumEventArgTypes.ENTITY] = entity
                context[ConiumEventArgTypes.INT] = entity.fireTicks
            }

            // Do extinguish when fire ticks is at last one.
            if (entity.fireTicks == 1) {
                entity.extinguish()
            }
        }
    }

    /**
     * Trigger the entity extinguishes events when entity fire extinguishing.
     *
     * @see EntityMixin
     * @see EntityMixin.onExtinguish
     * @see Entity.extinguish
     * @see ConiumEventType.ENTITY_EXTINGUISH_FIRE
     * @see ConiumEventType.ENTITY_EXTINGUISHED_FIRE
     *
     * @param entity the entity that extinguishing
     *
     * @return flag that noted should do mixin cancel
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun fireExtinguishEvent(entity: Entity): Boolean {
        // Only trigger event when fire ticks left at least 1.
        if (entity.fireTicks > 0) {
            // Only trigger event on server.
            return entity.entityWorld is ServerWorld && fireCascadedEvent(
                ConiumEventType.ENTITY_EXTINGUISH_FIRE,
                ConiumEventType.ENTITY_EXTINGUISHED_FIRE,
                entity.type,
                { extinguishContext: ConiumArisingEventContext<*, *> ->
                    // Fill extinguish context args.
                    extinguishContext[ConiumEventArgTypes.ENTITY] = entity
                    extinguishContext[ConiumEventArgTypes.INT] = entity.fireTicks
                },
                { extinguishingContext: ConiumArisingEventContext<*, *> ->
                    // Fill extinguished context args.
                    extinguishingContext[ConiumEventArgTypes.ENTITY] = entity
                }
            )
        }
        return false
    }

    /**
     * Trigger the entity start sprint.
     *
     * @see EntityMixin
     * @see EntityMixin.onSetSprint
     * @see Entity.baseTick
     * @see Entity.isSprinting
     * @see ConiumEventType.ENTITY_SPRINT
     * @see ConiumEventType.ENTITY_STOP_SPRINT
     *
     * @param entity the entity that extinguishing
     *
     * @return flag that noted should do mixin cancel
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun fireEntitySprintEvent(entity: Entity): Boolean {
        return fireEventCancelable(
            ConiumEventType.ENTITY_SPRINT,
            entity.type
        ) { context: ConiumArisingEventContext<*, *> ->
            // Fill the context args.
            context[ConiumEventArgTypes.ENTITY] = entity
        }
    }

    /**
     * Trigger the entity stop sprint.
     *
     * @see EntityMixin
     * @see EntityMixin.onSetSprint
     * @see Entity.baseTick
     * @see Entity.isSprinting
     * @see ConiumEventType.ENTITY_SPRINT
     * @see ConiumEventType.ENTITY_STOP_SPRINT
     *
     * @param entity the entity that extinguishing
     *
     * @return flag that noted should do mixin cancel
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun fireEntityStopSprintEvent(entity: Entity): Boolean {
        return fireEventCancelable(
            ConiumEventType.ENTITY_STOP_SPRINT,
            entity.type
        ) { context: ConiumArisingEventContext<*, *> ->
            // Fill the context args.
            context[ConiumEventArgTypes.ENTITY] = entity
        }
    }

    /**
     * Trigger the entity sprinting event when entity sprinting.
     *
     * @see EntityMixin
     * @see EntityMixin.onSprinting
     * @see Entity.baseTick
     * @see Entity.isSprinting
     * @see ConiumEventType.ENTITY_SPRINTING
     *
     * @param entity the entity that extinguishing
     *
     * @return flag that noted should do mixin cancel
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun fireEntitySprintingEvent(entity: Entity): Boolean {
        return fireEventCancelable(
            ConiumEventType.ENTITY_SPRINTING,
            entity.type
        ) { context: ConiumArisingEventContext<*, *> ->
            // Fill the context args.
            context[ConiumEventArgTypes.ENTITY] = entity
        }
    }

    /**
     * Trigger the entity tick event.
     *
     * @see Fluid.onScheduledTick
     * @see ConiumEventType.ENTITY_TICK
     * @see ConiumEventType.ENTITY_TICKED
     * @see ConiumEntityTickEvent
     *
     * @param entity the entity that is ticking
     *
     * @return flag that noted should do mixin cancel
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun fireEntityTickEvent(entity: Entity): Boolean {
        return fireEventCancelable(
            ConiumEventType.ENTITY_TICK,
            entity.type
        ) { blockScheduledTickContext ->
            // Fill the context args.
            blockScheduledTickContext[ConiumEventArgTypes.ENTITY] = entity
            blockScheduledTickContext[ConiumEventArgTypes.WORLD] = entity.entityWorld
        }
    }

    /**
     * Trigger the entity tick event.
     *
     * @see Fluid.onScheduledTick
     * @see ConiumEventType.ENTITY_TICK
     * @see ConiumEventType.ENTITY_TICKED
     * @see ConiumEntityTickedEvent
     *
     * @param entity the entity that is ticked
     *
     * @return flag that noted should do mixin cancel
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    @JvmStatic
    fun fireEntityTickedEvent(entity: Entity): Boolean {
        return fireEventCancelable(
            ConiumEventType.ENTITY_TICKED,
            entity.type
        ) { blockScheduledTickContext ->
            // Fill the context args.
            blockScheduledTickContext[ConiumEventArgTypes.ENTITY] = entity
            blockScheduledTickContext[ConiumEventArgTypes.WORLD] = entity.entityWorld
        }
    }
}