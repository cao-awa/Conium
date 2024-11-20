package com.github.cao.awa.conium.event

import com.github.cao.awa.conium.block.event.breaking.ConiumBreakBlockEvent
import com.github.cao.awa.conium.block.event.breaking.ConiumBreakingBlockEvent
import com.github.cao.awa.conium.block.event.breaking.ConiumBrokenBlockEvent
import com.github.cao.awa.conium.block.event.place.ConiumPlaceBlockEvent
import com.github.cao.awa.conium.block.event.place.ConiumPlacedBlockEvent
import com.github.cao.awa.conium.block.event.tick.ConiumBlockScheduleTickEvent
import com.github.cao.awa.conium.block.event.tick.ConiumBlockScheduleTickedEvent
import com.github.cao.awa.conium.block.event.tick.fluid.ConiumFluidScheduleTickEvent
import com.github.cao.awa.conium.block.event.tick.fluid.ConiumFluidScheduleTickedEvent
import com.github.cao.awa.conium.block.event.use.ConiumUseBlockEvent
import com.github.cao.awa.conium.block.event.use.ConiumUsedBlockEvent
import com.github.cao.awa.conium.entity.event.damage.ConiumEntityDamageEvent
import com.github.cao.awa.conium.entity.event.damage.ConiumEntityDamagedEvent
import com.github.cao.awa.conium.entity.event.die.ConiumEntityDeadEvent
import com.github.cao.awa.conium.entity.event.die.ConiumEntityDieEvent
import com.github.cao.awa.conium.entity.event.tick.ConiumEntityTickEvent
import com.github.cao.awa.conium.entity.event.tick.ConiumEntityTickedEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.server.tick.ConiumServerTickEvent
import com.github.cao.awa.conium.event.server.tick.ConiumServerTickTailEvent
import com.github.cao.awa.conium.event.trigger.ListTriggerable
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.use.ConiumItemUseOnBlockEvent
import com.github.cao.awa.conium.item.event.use.ConiumItemUsedOnBlockEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import java.util.*

abstract class ConiumEvent<P : ParameterSelective> : ListTriggerable<P>() {
    companion object {
        private val events: MutableMap<ConiumEventType<*>, ConiumEvent<*>> = CollectionFactor.hashMap()
        private val foreverContext: MutableMap<ConiumEventType<*>, MutableList<ConiumEventContext<*>>> = CollectionFactor.hashMap()

        @JvmField
        val itemUseOnBlockEvent = ConiumItemUseOnBlockEvent()

        @JvmField
        val itemUsedOnBlockEvent = ConiumItemUsedOnBlockEvent()

        @JvmField
        val serverTick = ConiumServerTickEvent()

        @JvmField
        val serverTickTail = ConiumServerTickTailEvent()

        @JvmField
        val breakingBlock = ConiumBreakingBlockEvent()

        @JvmField
        val brokenBlock = ConiumBrokenBlockEvent()

        @JvmField
        val breakBlock = ConiumBreakBlockEvent()

        @JvmField
        val placeBlock = ConiumPlaceBlockEvent()

        @JvmField
        val placedBlock = ConiumPlacedBlockEvent()

        @JvmField
        val useBlock = ConiumUseBlockEvent()

        @JvmField
        val usedBlock = ConiumUsedBlockEvent()

        @JvmField
        val entityTick = ConiumEntityTickEvent()

        @JvmField
        val entityTicked = ConiumEntityTickedEvent()

        @JvmField
        val entityDamage = ConiumEntityDamageEvent()

        @JvmField
        val entityDamaged = ConiumEntityDamagedEvent()

        @JvmField
        val entityDie = ConiumEntityDieEvent()

        @JvmField
        val entityDead = ConiumEntityDeadEvent()

        @JvmField
        val fluidScheduleTick = ConiumFluidScheduleTickEvent()

        @JvmField
        val fluidScheduleTicked = ConiumFluidScheduleTickedEvent()

        @JvmField
        val blockScheduleTick = ConiumBlockScheduleTickEvent()

        @JvmField
        val blockScheduleTicked = ConiumBlockScheduleTickedEvent()

        /**
         * Before event fires, create event context by requirements.
         *
         * @param type the type of event
         */
        @JvmStatic
        fun request(type: ConiumEventType<*>): ConiumEventContext<out ParameterSelective> {
            return this.events[type]!!.requirement()
        }

        @JvmStatic
        fun <X : ConiumEvent<X>> findEvent(type: ConiumEventType<*>): X {
            return this.events[type] as X
        }

        fun forever(eventType: ConiumEventType<*>, context: ConiumEventContext<*>) {
            this.foreverContext.computeIfAbsent(eventType) { CollectionFactor.arrayList() }.add(context)
        }

        fun forever(eventType: ConiumEventType<*>): MutableList<ConiumEventContext<*>> = this.foreverContext[eventType] ?: Collections.emptyList()

        fun resetForever() {
            this.foreverContext.clear()
        }

        @JvmStatic
        fun init() {
            this.events[ConiumEventType.ITEM_USE_ON_BLOCK] = this.itemUseOnBlockEvent
            this.events[ConiumEventType.ITEM_USED_ON_BLOCK] = this.itemUsedOnBlockEvent
            this.events[ConiumEventType.SERVER_TICK] = this.serverTick
            this.events[ConiumEventType.SERVER_TICK_TAIL] = this.serverTickTail

            this.events[ConiumEventType.BREAKING_BLOCK] = this.breakingBlock
            this.events[ConiumEventType.BREAK_BLOCK] = this.breakBlock
            this.events[ConiumEventType.BROKEN_BLOCK] = this.brokenBlock
            this.events[ConiumEventType.PLACE_BLOCK] = this.placeBlock
            this.events[ConiumEventType.PLACED_BLOCK] = this.placedBlock
            this.events[ConiumEventType.USE_BLOCK] = this.useBlock
            this.events[ConiumEventType.USED_BLOCK] = this.usedBlock

            this.events[ConiumEventType.ENTITY_TICK] = this.entityTick
            this.events[ConiumEventType.ENTITY_TICKED] = this.entityTicked
            this.events[ConiumEventType.ENTITY_DAMAGE] = this.entityDamage
            this.events[ConiumEventType.ENTITY_DAMAGED] = this.entityDamaged
            this.events[ConiumEventType.ENTITY_DIE] = this.entityDie
            this.events[ConiumEventType.ENTITY_DEAD] = this.entityDead

            this.events[ConiumEventType.FLUID_SCHEDULE_TICK] = this.fluidScheduleTick
            this.events[ConiumEventType.FLUID_SCHEDULE_TICKED] = this.fluidScheduleTicked
            this.events[ConiumEventType.BLOCK_SCHEDULE_TICK] = this.blockScheduleTick
            this.events[ConiumEventType.BLOCK_SCHEDULE_TICKED] = this.blockScheduleTicked
        }

        fun clearEntitySubscribes() {
            this.entityTick.clearSubscribes()
            this.entityTicked.clearSubscribes()
            this.entityDamage.clearSubscribes()
            this.entityDamaged.clearSubscribes()
            this.entityDie.clearSubscribes()
            this.entityDead.clearSubscribes()
        }

        fun clearItemSubscribes() {
            this.itemUseOnBlockEvent.clearSubscribes()
            this.itemUsedOnBlockEvent.clearSubscribes()
        }

        fun clearServerTickSubscribes() {
            this.serverTick.clearSubscribes()
        }

        fun clearBlockSubscribes() {
            this.breakingBlock.clearSubscribes()
            this.breakBlock.clearSubscribes()
            this.brokenBlock.clearSubscribes()
            this.placeBlock.clearSubscribes()
            this.placedBlock.clearSubscribes()
            this.useBlock.clearSubscribes()
            this.usedBlock.clearSubscribes()

            this.fluidScheduleTick.clearSubscribes()
            this.fluidScheduleTicked.clearSubscribes()
            this.blockScheduleTick.clearSubscribes()
            this.blockScheduleTicked.clearSubscribes()
        }
    }

    abstract fun requirement(): ConiumEventContext<out ParameterSelective>
}
