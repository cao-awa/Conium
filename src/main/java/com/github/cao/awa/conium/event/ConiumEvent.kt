package com.github.cao.awa.conium.event

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.trigger.ListTriggerable
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.item.event.use.ConiumItemUseOnBlockEvent
import com.github.cao.awa.conium.parameter.ParameterSelective

abstract class ConiumEvent<P: ParameterSelective> : ListTriggerable<P>() {
    companion object {
        private val events: MutableMap<ConiumEventType, ConiumEvent<*>> = ApricotCollectionFactor.hashMap()
        @JvmField
        val itemUseOnBlockEvent = ConiumItemUseOnBlockEvent()

        @JvmStatic
        fun fire(type: ConiumEventType): ConiumEventContext<out ParameterSelective> {
            return events[type]!!.requirement()
        }

        @JvmStatic
        fun <X: ConiumEvent<X>> findEvent(type: ConiumEventType): X {
            return events[type] as X
        }

        @JvmStatic
        fun init() {
            events[ConiumEventType.ITEM_USE_ON_BLOCK] = itemUseOnBlockEvent
        }

        fun clearSubscribes() {
            itemUseOnBlockEvent.clearSubscribes()
        }
    }

    abstract fun requirement(): ConiumEventContext<out ParameterSelective>
}
