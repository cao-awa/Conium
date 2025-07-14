package com.github.cao.awa.conium.dsl

import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventType

class DSLEventMetadata<I: Any, M: ConiumEventMetadata, T: ConiumEventType<I, M>> {
    var target: T? = null
        set(value) {
            if (field != null) {
                throw IllegalStateException("The event target already specified, cannot set a new value")
            }
            field = value
        }

    constructor()

    constructor(eventType: T) {
       this.target = eventType
    }

    fun action(action: M.() -> Boolean) {
        println("awa")
        ConiumEventContextBuilder.requestDsl(this.target!!, action)
    }
}

