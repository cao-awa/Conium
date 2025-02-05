package com.github.cao.awa.conium.intermediary.mixin.entity

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.intermediary.mixin.ConiumEventMixinIntermediary
import com.github.cao.awa.conium.intermediary.mixin.ConiumEventMixinIntermediary.Companion.fireCascadedEvent
import com.github.cao.awa.conium.intermediary.mixin.ConiumEventMixinIntermediary.Companion.fireEvent
import com.github.cao.awa.conium.intermediary.mixin.ConiumEventMixinIntermediary.Companion.fireEventCancelable
import com.github.cao.awa.conium.mixin.entity.EntityMixin
import com.github.cao.awa.conium.mixin.entity.living.LivingEntityMixin
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
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
class ConiumEntityEventMixinIntermediary {
    companion object {
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
        fun fireEntityDamageEvent(eventType: ConiumEventType<EntityType<*>>, entity: LivingEntity, damageSource: DamageSource, amount: Float): Boolean {
            return fireEventCancelable(
                eventType,
                entity.type
            ) { damageContext ->
                // Fill the context args.
                damageContext[ConiumEventArgTypes.WORLD] = entity.world
                damageContext[ConiumEventArgTypes.DAMAGE_SOURCE] = damageSource
                damageContext[ConiumEventArgTypes.LIVING_ENTITY] = entity
                damageContext[ConiumEventArgTypes.FLOAT] = amount
            }
        }

        /**
         * Trigger the entity dying events when entity dying or dead.
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
        fun fireEntityDyeEvent(eventType: ConiumEventType<EntityType<*>>, entity: LivingEntity, damageSource: DamageSource): Boolean {
            return fireEventCancelable(
                eventType,
                entity.type
            ) { dyeContext ->
                // Fill the context args.
                dyeContext[ConiumEventArgTypes.DAMAGE_SOURCE] = damageSource
                dyeContext[ConiumEventArgTypes.LIVING_ENTITY] = entity
            }
        }

        /**
         * Trigger the entity dying events when entity dying or dead.
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
        fun fireEntityRestEvent(eventType: ConiumEventType<EntityType<*>>, entity: LivingEntity, sleepPos: BlockPos?): Boolean {
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
            ) { dyeContext ->
                // Fill the context args.
                dyeContext[ConiumEventArgTypes.LIVING_ENTITY] = entity
                dyeContext[ConiumEventArgTypes.BLOCK_POS] = sleepPos
                dyeContext[ConiumEventArgTypes.WORLD] = entity.world
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
                ) { context: ConiumEventContext<*> ->
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
                return entity.world is ServerWorld && fireCascadedEvent(
                    ConiumEventType.ENTITY_EXTINGUISH_FIRE,
                    ConiumEventType.ENTITY_EXTINGUISHED_FIRE,
                    entity.type,
                    { extinguishContext: ConiumEventContext<*> ->
                        // Fill extinguish context args.
                        extinguishContext[ConiumEventArgTypes.ENTITY] = entity
                        extinguishContext[ConiumEventArgTypes.INT] = entity.fireTicks
                    },
                    { extinguishingContext: ConiumEventContext<*> ->
                        // Fill extinguished context args.
                        extinguishingContext[ConiumEventArgTypes.ENTITY] = entity
                    }
                )
            }
            return false
        }

        /**
         * Trigger the entity sprint state change events when entity start sprint or stop sprinting.
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
        fun fireEntitySprintsEvent(targetEvent: ConiumEventType<EntityType<*>>, entity: Entity): Boolean {
            return fireEventCancelable(
                targetEvent,
                entity.type
            ) { context: ConiumEventContext<*> ->
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
            ) { context: ConiumEventContext<*> ->
                // Fill the context args.
                context[ConiumEventArgTypes.ENTITY] = entity
            }
        }
    }
}
