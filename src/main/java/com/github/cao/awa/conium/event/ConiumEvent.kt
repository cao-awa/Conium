package com.github.cao.awa.conium.event

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.trigger.ListTriggerable
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.use.ConiumItemUseOnBlockEvent
import com.github.cao.awa.conium.parameter.ParameterSelective
import java.util.*

abstract class ConiumEvent<P: ParameterSelective> : ListTriggerable<P>() {
    companion object {
        private val events: MutableMap<ConiumEventType, ConiumEvent<*>> = ApricotCollectionFactor.hashMap()
        private val foreverContext: MutableMap<ConiumEventType, MutableList<ConiumEventContext<*>>> = ApricotCollectionFactor.hashMap()
        @JvmField
        val itemUseOnBlockEvent = ConiumItemUseOnBlockEvent()

        /**
         * Before event fires, create event context by requirements.
         *
         * @param type the type of event
         */
        @JvmStatic
        fun request(type: ConiumEventType): ConiumEventContext<out ParameterSelective> {
            return this.events[type]!!.requirement()
        }

        @JvmStatic
        fun <X: ConiumEvent<X>> findEvent(type: ConiumEventType): X {
            return this.events[type] as X
        }

        fun forever(eventType: ConiumEventType, context: ConiumEventContext<*>) {
            this.foreverContext.computeIfAbsent(eventType, {ApricotCollectionFactor.arrayList()}).add(context)
        }

        fun forever(eventType: ConiumEventType): MutableList<ConiumEventContext<*>> = this.foreverContext[eventType] ?: Collections.emptyList()

        fun resetForever() {
            this.foreverContext.clear()
        }

        @JvmStatic
        fun init() {
            this.events[ConiumEventType.ITEM_USE_ON_BLOCK] = itemUseOnBlockEvent
        }

        fun clearSubscribes() {
            this.itemUseOnBlockEvent.clearSubscribes()
        }
    }

    abstract fun requirement(): ConiumEventContext<out ParameterSelective>
}
