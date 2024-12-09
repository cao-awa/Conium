package com.github.cao.awa.conium.event

import com.github.cao.awa.conium.block.entity.event.chest.close.ConiumChestClosedEvent
import com.github.cao.awa.conium.block.entity.event.chest.close.ConiumChestClosingEvent
import com.github.cao.awa.conium.block.entity.event.chest.close.trapped.ConiumTrappedChestClosedEvent
import com.github.cao.awa.conium.block.entity.event.chest.close.trapped.ConiumTrappedChestClosingEvent
import com.github.cao.awa.conium.block.entity.event.chest.open.ConiumChestOpenedEvent
import com.github.cao.awa.conium.block.entity.event.chest.open.ConiumChestOpeningEvent
import com.github.cao.awa.conium.block.entity.event.chest.open.trapped.ConiumTrappedChestOpenedEvent
import com.github.cao.awa.conium.block.entity.event.chest.open.trapped.ConiumTrappedChestOpeningEvent
import com.github.cao.awa.conium.block.entity.event.shulker.close.ConiumShulkerBoxClosedEvent
import com.github.cao.awa.conium.block.entity.event.shulker.close.ConiumShulkerBoxClosingEvent
import com.github.cao.awa.conium.block.entity.event.shulker.open.ConiumShulkerBoxOpenedEvent
import com.github.cao.awa.conium.block.entity.event.shulker.open.ConiumShulkerBoxOpeningEvent
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
import com.github.cao.awa.conium.event.server.tick.ConiumServerTickEvent
import com.github.cao.awa.conium.event.server.tick.ConiumServerTickTailEvent
import com.github.cao.awa.conium.event.trigger.ListTriggerable
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
        val itemUseOnBlockEvent: ConiumItemUseOnBlockEvent = ConiumItemUseOnBlockEvent()

        @JvmField
        val itemUsedOnBlockEvent: ConiumItemUsedOnBlockEvent = ConiumItemUsedOnBlockEvent()

        @JvmField
        val serverTick: ConiumServerTickEvent = ConiumServerTickEvent()

        @JvmField
        val serverTickTail: ConiumServerTickTailEvent = ConiumServerTickTailEvent()

        @JvmField
        val breakingBlock: ConiumBreakingBlockEvent = ConiumBreakingBlockEvent()

        @JvmField
        val brokenBlock: ConiumBrokenBlockEvent = ConiumBrokenBlockEvent()

        @JvmField
        val breakBlock: ConiumBreakBlockEvent = ConiumBreakBlockEvent()

        @JvmField
        val placeBlock: ConiumPlaceBlockEvent = ConiumPlaceBlockEvent()

        @JvmField
        val placedBlock: ConiumPlacedBlockEvent = ConiumPlacedBlockEvent()

        @JvmField
        val useBlock: ConiumUseBlockEvent = ConiumUseBlockEvent()

        @JvmField
        val usedBlock: ConiumUsedBlockEvent = ConiumUsedBlockEvent()

        @JvmField
        val entityTick: ConiumEntityTickEvent = ConiumEntityTickEvent()

        @JvmField
        val entityTicked: ConiumEntityTickedEvent = ConiumEntityTickedEvent()

        @JvmField
        val entityDamage: ConiumEntityDamageEvent = ConiumEntityDamageEvent()

        @JvmField
        val entityDamaged: ConiumEntityDamagedEvent = ConiumEntityDamagedEvent()

        @JvmField
        val entityDie: ConiumEntityDieEvent = ConiumEntityDieEvent()

        @JvmField
        val entityDead: ConiumEntityDeadEvent = ConiumEntityDeadEvent()

        @JvmField
        val fluidScheduleTick: ConiumFluidScheduleTickEvent = ConiumFluidScheduleTickEvent()

        @JvmField
        val fluidScheduleTicked: ConiumFluidScheduleTickedEvent = ConiumFluidScheduleTickedEvent()

        @JvmField
        val blockScheduleTick: ConiumBlockScheduleTickEvent = ConiumBlockScheduleTickEvent()

        @JvmField
        val blockScheduleTicked: ConiumBlockScheduleTickedEvent = ConiumBlockScheduleTickedEvent()

        @JvmField
        val shulkerBoxOpening: ConiumShulkerBoxOpeningEvent = ConiumShulkerBoxOpeningEvent()

        @JvmField
        val shulkerBoxOpened: ConiumShulkerBoxOpenedEvent = ConiumShulkerBoxOpenedEvent()

        @JvmField
        val shulkerBoxClosing: ConiumShulkerBoxClosingEvent = ConiumShulkerBoxClosingEvent()

        @JvmField
        val shulkerBoxClosed: ConiumShulkerBoxClosedEvent = ConiumShulkerBoxClosedEvent()

        @JvmField
        val chestOpening: ConiumChestOpeningEvent = ConiumChestOpeningEvent()

        @JvmField
        val chestOpened: ConiumChestOpenedEvent = ConiumChestOpenedEvent()

        @JvmField
        val chestClosing: ConiumChestClosingEvent = ConiumChestClosingEvent()

        @JvmField
        val chestClosed: ConiumChestClosedEvent = ConiumChestClosedEvent()

        @JvmField
        val trappedChestOpening: ConiumTrappedChestOpeningEvent = ConiumTrappedChestOpeningEvent()

        @JvmField
        val trappedChestOpened: ConiumTrappedChestOpenedEvent = ConiumTrappedChestOpenedEvent()

        @JvmField
        val trappedChestClosing: ConiumTrappedChestClosingEvent = ConiumTrappedChestClosingEvent()

        @JvmField
        val trappedChestClosed: ConiumTrappedChestClosedEvent = ConiumTrappedChestClosedEvent()

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

        fun count(): Int = this.events.size

        fun events(): Map<ConiumEventType<*>, ConiumEvent<*>> = Collections.unmodifiableMap(this.events)

        fun forever(eventType: ConiumEventType<*>, context: ConiumEventContext<*>) {
            this.foreverContext.computeIfAbsent(eventType) { CollectionFactor.arrayList() }.add(context)
        }

        fun forever(eventType: ConiumEventType<*>): MutableList<ConiumEventContext<*>> {
            return this.foreverContext.getOrDefault(eventType, Collections.emptyList())
        }

        fun resetForever() {
            this.foreverContext.clear()
        }

        fun attach() {
            for ((_: ConiumEventType<*>, event: ConiumEvent<*>) in this.events) {
                event.attach()
            }
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

            this.events[ConiumEventType.SHULKER_BOX_OPENING] = this.shulkerBoxOpening
            this.events[ConiumEventType.SHULKER_BOX_OPENED] = this.shulkerBoxOpened
            this.events[ConiumEventType.SHULKER_BOX_CLOSING] = this.shulkerBoxClosing
            this.events[ConiumEventType.SHULKER_BOX_CLOSED] = this.shulkerBoxClosed
            this.events[ConiumEventType.CHEST_OPENING] = this.chestOpening
            this.events[ConiumEventType.CHEST_OPENED] = this.chestOpened
            this.events[ConiumEventType.CHEST_CLOSING] = this.chestClosing
            this.events[ConiumEventType.CHEST_CLOSED] = this.chestClosed
            this.events[ConiumEventType.TRAPPED_CHEST_OPENING] = this.trappedChestOpening
            this.events[ConiumEventType.TRAPPED_CHEST_OPENED] = this.trappedChestOpened
            this.events[ConiumEventType.TRAPPED_CHEST_CLOSING] = this.trappedChestClosing
            this.events[ConiumEventType.TRAPPED_CHEST_CLOSED] = this.trappedChestClosed
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

            this.shulkerBoxOpening.clearSubscribes()
            this.shulkerBoxOpened.clearSubscribes()
            this.shulkerBoxClosing.clearSubscribes()
            this.shulkerBoxClosed.clearSubscribes()
            this.chestOpening.clearSubscribes()
            this.chestOpened.clearSubscribes()
            this.chestClosing.clearSubscribes()
            this.chestClosed.clearSubscribes()
            this.trappedChestOpening.clearSubscribes()
            this.trappedChestOpened.clearSubscribes()
            this.trappedChestClosing.clearSubscribes()
            this.trappedChestClosed.clearSubscribes()
        }
    }

    abstract fun requirement(): ConiumEventContext<out ParameterSelective>

    open fun attach() {
        // No default attaches.
    }
}
