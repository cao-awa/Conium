package com.github.cao.awa.conium.event

import com.github.cao.awa.conium.block.event.breaking.ConiumBreakBlockEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.server.tick.ConiumServerTickEvent
import com.github.cao.awa.conium.event.trigger.ListTriggerable
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.use.ConiumItemUseOnBlockEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import java.util.*

abstract class ConiumEvent<P : ParameterSelective> : ListTriggerable<P>() {
    companion object {
        private val events: MutableMap<ConiumEventType, ConiumEvent<*>> = CollectionFactor.hashMap()
        private val foreverContext: MutableMap<ConiumEventType, MutableList<ConiumEventContext<*, Boolean>>> = CollectionFactor.hashMap()

        @JvmField
        val itemUseOnBlockEvent = ConiumItemUseOnBlockEvent()

        @JvmField
        val serverTick = ConiumServerTickEvent()

        @JvmField
        val breakBlock = ConiumBreakBlockEvent()

        /**
         * Before event fires, create event context by requirements.
         *
         * @param type the type of event
         */
        @JvmStatic
        fun request(type: ConiumEventType): ConiumEventContext<out ParameterSelective, Boolean> {
            return this.events[type]!!.requirement()
        }

        @JvmStatic
        fun <X : ConiumEvent<X>> findEvent(type: ConiumEventType): X {
            return this.events[type] as X
        }

        fun forever(eventType: ConiumEventType, context: ConiumEventContext<*, Boolean>) {
            this.foreverContext.computeIfAbsent(eventType) { CollectionFactor.arrayList() }.add(context)
        }

        fun forever(eventType: ConiumEventType): MutableList<ConiumEventContext<*, Boolean>> = this.foreverContext[eventType] ?: Collections.emptyList()

        fun resetForever() {
            this.foreverContext.clear()
        }

        @JvmStatic
        fun init() {
            this.events[ConiumEventType.ITEM_USE_ON_BLOCK] = this.itemUseOnBlockEvent
            this.events[ConiumEventType.SERVER_TICK] = this.serverTick
            this.events[ConiumEventType.BREAK_BLOCK] = this.breakBlock
        }

        fun clearItemSubscribes() {
            this.itemUseOnBlockEvent.clearSubscribes()
        }

        fun clearServerTickSubscribes() {
            this.serverTick.clearSubscribes()
        }

        fun clearBlockSubscribes() {
            this.breakBlock.clearSubscribes()
        }
    }

    abstract fun requirement(): ConiumEventContext<out ParameterSelective, Boolean>
}
