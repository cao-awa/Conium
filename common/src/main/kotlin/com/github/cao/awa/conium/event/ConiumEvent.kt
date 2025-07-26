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
import com.github.cao.awa.conium.chunk.event.receive.ConiumReceiveChunkEvent
import com.github.cao.awa.conium.chunk.event.receive.ConiumReceivedChunkEvent
import com.github.cao.awa.conium.entity.event.damage.ConiumEntityDamageEvent
import com.github.cao.awa.conium.entity.event.damage.ConiumEntityDamagedEvent
import com.github.cao.awa.conium.entity.event.die.ConiumEntityDeadEvent
import com.github.cao.awa.conium.entity.event.die.ConiumEntityDieEvent
import com.github.cao.awa.conium.entity.event.fire.ConiumEntityOnFireEvent
import com.github.cao.awa.conium.entity.event.fire.extinguish.ConiumEntityExtinguishFireEvent
import com.github.cao.awa.conium.entity.event.fire.extinguish.ConiumEntityExtinguishedFireEvent
import com.github.cao.awa.conium.entity.event.rest.sleep.ConiumEntitySleepEvent
import com.github.cao.awa.conium.entity.event.rest.sleep.ConiumEntityTrySleepEvent
import com.github.cao.awa.conium.entity.event.rest.wake.ConiumEntityWakeUpEvent
import com.github.cao.awa.conium.entity.event.rest.wake.ConiumEntityWakedUpEvent
import com.github.cao.awa.conium.entity.event.sprint.ConiumEntitySprintEvent
import com.github.cao.awa.conium.entity.event.sprint.ConiumEntitySprintingEvent
import com.github.cao.awa.conium.entity.event.sprint.ConiumEntityStopSprintEvent
import com.github.cao.awa.conium.entity.event.tick.ConiumEntityTickEvent
import com.github.cao.awa.conium.entity.event.tick.ConiumEntityTickedEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder.unnamed
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.inactive.ConiumInactiveEvent
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.trigger.ConiumEventTrigger
import com.github.cao.awa.conium.event.trigger.ListTriggerable
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.stack.click.ConiumItemStackClickEvent
import com.github.cao.awa.conium.item.event.stack.click.ConiumItemStackClickedEvent
import com.github.cao.awa.conium.item.event.tick.inventory.ConiumItemInventoryTickEvent
import com.github.cao.awa.conium.item.event.tick.inventory.ConiumItemInventoryTickedEvent
import com.github.cao.awa.conium.item.event.use.ConiumItemUseEvent
import com.github.cao.awa.conium.item.event.use.ConiumItemUsedEvent
import com.github.cao.awa.conium.item.event.use.block.ConiumItemUseOnBlockEvent
import com.github.cao.awa.conium.item.event.use.block.ConiumItemUsedOnBlockEvent
import com.github.cao.awa.conium.item.event.use.entity.ConiumItemUseOnEntityEvent
import com.github.cao.awa.conium.item.event.use.entity.ConiumItemUsedOnEntityEvent
import com.github.cao.awa.conium.item.event.use.usage.ConiumItemUsageTickEvent
import com.github.cao.awa.conium.item.event.use.usage.ConiumItemUsageTickedEvent
import com.github.cao.awa.conium.kotlin.extent.manipulate.doCast
import com.github.cao.awa.conium.network.event.ConiumServerConfigurationConnectionEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.random.event.ConiumRandomEvent
import com.github.cao.awa.conium.script.index.common.unnamed
import com.github.cao.awa.conium.server.event.random.ConiumServerRandomEvent
import com.github.cao.awa.conium.server.event.tick.ConiumServerTickEvent
import com.github.cao.awa.conium.server.event.tick.ConiumServerTickTailEvent
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.*

abstract class ConiumEvent<
        I: Any,
        M: ConiumEventMetadata<I>,
        P: ParameterSelective
>(
    val eventType: ConiumEventType<I, M, *, *>,
    val nextEvent: () -> ConiumEventType<*, *, *, *> = { ConiumEventType.INACTIVE }
) : ListTriggerable<P>() {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumEvent")
        private val events: MutableMap<ConiumEventType<*, *, *, *>, ConiumEvent<*, *, *>> = CollectionFactor.hashMap()
        private val foreverContext: MutableMap<ConiumEventType<*, *, *, *>, MutableList<ConiumArisingEventContext<*, *>>> = CollectionFactor.hashMap()

        @JvmField
        val random: ConiumRandomEvent = ConiumRandomEvent()

        @JvmField
        val itemUse: ConiumItemUseEvent = ConiumItemUseEvent()

        @JvmField
        val itemUsed: ConiumItemUsedEvent = ConiumItemUsedEvent()

        @JvmField
        val itemUseOnBlock: ConiumItemUseOnBlockEvent = ConiumItemUseOnBlockEvent()

        @JvmField
        val itemUsedOnBlock: ConiumItemUsedOnBlockEvent = ConiumItemUsedOnBlockEvent()

        @JvmField
        val itemUseOnEntity: ConiumItemUseOnEntityEvent = ConiumItemUseOnEntityEvent()

        @JvmField
        val itemUsedOnEntity: ConiumItemUsedOnEntityEvent = ConiumItemUsedOnEntityEvent()

        @JvmField
        val itemUsageTick: ConiumItemUsageTickEvent = ConiumItemUsageTickEvent()

        @JvmField
        val itemUsageTicked: ConiumItemUsageTickedEvent = ConiumItemUsageTickedEvent()

        @JvmField
        val itemStackClick: ConiumItemStackClickEvent = ConiumItemStackClickEvent()

        @JvmField
        val itemStackClicked: ConiumItemStackClickedEvent = ConiumItemStackClickedEvent()

        @JvmField
        val itemInventoryTick: ConiumItemInventoryTickEvent = ConiumItemInventoryTickEvent()

        @JvmField
        val itemInventoryTicked: ConiumItemInventoryTickedEvent = ConiumItemInventoryTickedEvent()

        @JvmField
        val serverTick: ConiumServerTickEvent = ConiumServerTickEvent()

        @JvmField
        val serverRandom: ConiumServerRandomEvent = ConiumServerRandomEvent()

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
        val entityTrySleep: ConiumEntityTrySleepEvent = ConiumEntityTrySleepEvent()

        @JvmField
        val entitySleep: ConiumEntitySleepEvent = ConiumEntitySleepEvent()

        @JvmField
        val entityWakeUp: ConiumEntityWakeUpEvent = ConiumEntityWakeUpEvent()

        @JvmField
        val entityWakedUp: ConiumEntityWakedUpEvent = ConiumEntityWakedUpEvent()

        @JvmField
        val entitySprint: ConiumEntitySprintEvent = ConiumEntitySprintEvent()

        @JvmField
        val entitySprinting: ConiumEntitySprintingEvent = ConiumEntitySprintingEvent()

        @JvmField
        val entityStopSprint: ConiumEntityStopSprintEvent = ConiumEntityStopSprintEvent()

        @JvmField
        val entityOnFire: ConiumEntityOnFireEvent = ConiumEntityOnFireEvent()

        @JvmField
        val entityExtinguishFire: ConiumEntityExtinguishFireEvent = ConiumEntityExtinguishFireEvent()

        @JvmField
        val entityExtinguishedFire: ConiumEntityExtinguishedFireEvent = ConiumEntityExtinguishedFireEvent()

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

        @JvmField
        val receiveChunk: ConiumReceiveChunkEvent = ConiumReceiveChunkEvent()

        @JvmField
        val receivedChunk: ConiumReceivedChunkEvent = ConiumReceivedChunkEvent()

        @JvmField
        val enterConfigurationConnection: ConiumServerConfigurationConnectionEvent = ConiumServerConfigurationConnectionEvent()

        /**
         * Before event fires, create event context by requirements.
         *
         * @param type the type of event
         */
        @JvmStatic
        fun <I: Any> request(type: ConiumEventType<I, out ConiumEventMetadata<I>, *, *>): ConiumArisingEventContext<*, out ParameterSelective> {
            return findEvent(type).request()
        }

        @JvmStatic
        fun <I: Any, M: ConiumEventMetadata<I>> findEvent(type: ConiumEventType<I, M, *, *>): ConiumEvent<I, M, *> {
            return this.events[type].doCast()
        }

        @JvmStatic
        fun unsafeFindEvent(type: ConiumEventType<*, *, *, *>): ConiumEvent<*, *, *> {
            return this.events[type] as ConiumEvent<*, *, *>
        }

        fun count(): Int = this.events.size

        fun events(): Map<ConiumEventType<*, *, *, *>, ConiumEvent<*, *, *>> = Collections.unmodifiableMap(this.events)

        fun <I: Any> forever(eventType: ConiumEventType<I, out ConiumEventMetadata<I>, *, *>, context: ConiumArisingEventContext<*, *>) {
            this.foreverContext.computeIfAbsent(eventType) { CollectionFactor.arrayList() }.add(context)
        }

        fun forever(eventType: ConiumEventType<*, *, *, *>): MutableList<ConiumArisingEventContext<*, *>> {
            return this.foreverContext.getOrDefault(eventType, Collections.emptyList())
        }

        fun resetForever() {
            this.foreverContext.clear()
        }

        fun attach() {
            for ((_: ConiumEventType<*, *, *, *>, event: ConiumEvent<*, *, *>) in this.events) {
                event.attach()
            }
        }

        @JvmStatic
        fun init() {

        }

        @JvmStatic
        fun clearAll() {
            clearEntitySubscribes()
            clearChunkSubscribes()
            clearRandomSubscribes()
            clearNetworkSubscribes()
            clearItemSubscribes()
            clearBlockSubscribes()
            clearServerTickSubscribes()
        }

        fun clearEntitySubscribes() {
            this.entityTick.clearSubscribes()
            this.entityTicked.clearSubscribes()
            this.entityDamage.clearSubscribes()
            this.entityDamaged.clearSubscribes()
            this.entityDie.clearSubscribes()
            this.entityDead.clearSubscribes()
            this.entityTrySleep.clearSubscribes()
            this.entitySleep.clearSubscribes()
            this.entityWakeUp.clearSubscribes()
            this.entityWakedUp.clearSubscribes()
            this.entitySprint.clearSubscribes()
            this.entitySprinting.clearSubscribes()
            this.entityStopSprint.clearSubscribes()
            this.entityOnFire.clearSubscribes()
            this.entityExtinguishFire.clearSubscribes()
            this.entityExtinguishedFire.clearSubscribes()
        }

        fun clearItemSubscribes() {
            this.itemUse.clearSubscribes()
            this.itemUsed.clearSubscribes()

            this.itemUseOnBlock.clearSubscribes()
            this.itemUsedOnBlock.clearSubscribes()
            this.itemUseOnEntity.clearSubscribes()
            this.itemUsedOnEntity.clearSubscribes()

            this.itemUsageTick.clearSubscribes()
            this.itemUsageTicked.clearSubscribes()
            this.itemStackClick.clearSubscribes()
            this.itemStackClicked.clearSubscribes()
            this.itemInventoryTick.clearSubscribes()
            this.itemInventoryTicked.clearSubscribes()
        }

        fun clearServerTickSubscribes() {
            this.serverTick.clearSubscribes()
            this.serverRandom.clearSubscribes()
            this.serverTickTail.clearSubscribes()
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

        fun clearRandomSubscribes() {
            this.random.clearSubscribes()
        }

        fun clearChunkSubscribes() {
            this.receiveChunk.clearSubscribes()
            this.receivedChunk.clearSubscribes()
        }

        fun clearNetworkSubscribes() {
            this.enterConfigurationConnection.clearSubscribes()
        }
    }

    private val listeners: MutableList<ConiumEventTrigger<I, M>> = CollectionFactor.arrayList()

    init {
        register()
    }

    private fun register() {
        events[this.eventType]?.let { event: ConiumEvent<*, *, *> ->
            if (!shouldForceOverride()) {
                throw IllegalStateException("The event type '${this.eventType.name}' already registered as '${event.javaClass.name}', '${this.javaClass.name}' cannot override it")
            } else {
                LOGGER.info(
                    "The event type '{}' already registered as '{}', '{}' force override it now",
                    this.eventType.name,
                    event.javaClass.name,
                    this.javaClass.name
                )
            }
        }
        events[this.eventType] = this
    }

    fun request(): ConiumArisingEventContext<I, out ParameterSelective> {
        return requirement().attaches(
            forever(this.eventType)
        ).also { context: ConiumArisingEventContext<I, out ParameterSelective> ->
            context.attach(unnamed {
                val metadata: M = metadata(context)
                this.listeners.forEach { listener: ConiumEventTrigger<I, M> ->
                    if (listener.alwaysCallback || listener.targetIdentity(context.identity.doCast())) {
                        listener.callback(metadata)
                    }
                }
                false
            })
        }
    }

    fun listen(callback: (M) -> Unit) {
        this.listeners.add(
            ConiumEventTrigger(callback, { true }, true)
        )
    }

    fun listen(identity: Any?, callback: (M) -> Unit) {
        this.listeners.add(
            ConiumEventTrigger(
                callback,
                { it == identity },
                false
            )
        )
    }

    abstract fun metadata(context: ConiumEventContext<I>): M

    abstract fun requirement(): ConiumArisingEventContext<I, out ParameterSelective>

    open fun attach() {
        // No default attaches.
    }

    open fun shouldForceOverride(): Boolean = false
}
