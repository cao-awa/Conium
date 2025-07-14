package com.github.cao.awa.conium.dsl

import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventType

open class DSLEventMetadata<I: Any, M: ConiumEventMetadata, T: ConiumEventType<I, M>> {
    private var catcher: (DSLEventExceptionMetadata<I, M, T>.() -> Unit)? = null
    private var finalizer: (M.() -> Unit)? = null
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

    fun action(handler: M.() -> Boolean) {
        println("awa")
        ConiumEventContextBuilder.requestDsl(this.target!!) {
            val result: Result<Boolean> = runCatching {
                handler(this)
            }

            catcher?.let {
                result.exceptionOrNull()?.let { exception: Throwable ->
                    it(DSLEventExceptionMetadata(exception, this))
                }
            }

            finalizer?.let {
                it(this)
            }

            result.isSuccess
        }
    }

    fun catching(handler: DSLEventExceptionMetadata<I, M, T>.() -> Unit) {
        this.catcher = handler
    }

    fun finalize(handler: M.() -> Unit) {
        this.finalizer = handler
    }
}

