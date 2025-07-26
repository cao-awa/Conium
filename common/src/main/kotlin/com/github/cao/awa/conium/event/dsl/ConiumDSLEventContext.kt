package com.github.cao.awa.conium.event.dsl

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.extend.ConiumExtends.ifException
import com.github.cao.awa.conium.kotlin.extent.manipulate.doCast
import com.github.cao.awa.conium.parameter.ParameterSelective
import com.github.cao.awa.conium.threadpool.ConiumThreadPool
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import java.util.function.Consumer

open class ConiumDSLEventContext<I : Any, M : ConiumEventMetadata<I>, N: ConiumEventMetadata<I>, T : ConiumEventType<I, M, *, N>>(val event: ConiumEvent<I, M, *>): ConiumEventContext<I>() {
    companion object {
        fun <I: Any, M: ConiumEventMetadata<I>, N: ConiumEventMetadata<I>, T: ConiumEventType<I, M, *, N>> onEvent(
            eventType: T,
            block: ConiumDSLEventContext<I, M, N, T>.() -> Unit
        ): ConiumDSLEventContext<I, M, N, T> {
            return ConiumDSLEventContext(eventType, ConiumEvent.findEvent(eventType)).also { dslEventMetadata: ConiumDSLEventContext<I, M, N, T> ->
                ConiumEvent.findEvent(eventType).listen {
                    dslEventMetadata.doAction(it)
                }

                block(dslEventMetadata)
            }
        }
    }

    private var catcher: Consumer<Throwable>? = null
        set(value) {
            if (warningNoRepeats(field, "event exception catcher")) {
                field = value
            }
        }
    private var handler: (M.() -> Boolean)? = null
        set(value) {
            if (warningNoRepeats(field, "event handler")) {
                field = value
            }
        }
    private var specifyCatchers: MutableMap<Class<out Throwable>, MutableList<Consumer<out Throwable>>> = CollectionFactor.hashMap()
    private var finalizer: (M.() -> Unit)? = null
        set(value) {
            if (warningNoRepeats(field, "event finalizer")) {
                field = value
            }
        }
    private var target: T? = null
        set(value) {
            if (warningNoRepeats(field, "event target")) {
                field = value
            }
        }

    constructor(eventType: T, event: ConiumEvent<I, M, *>) : this(event) {
        this.target = eventType
    }

    fun action(handler: M.() -> Boolean) {
        this.handler = handler
    }

    fun doAction(metadata: M): Boolean {
        if (this.async) {
            ConiumThreadPool.execute {
                execute(metadata)
            }
            return true
        } else {
            return execute(metadata)
        }
    }

    private fun execute(metadata: M): Boolean {
        if (this.handler == null) {
            return true
        }

        val result = this.handler.let { handler ->
            runCatching {
                this.handler!!(metadata)
            }.ifException(true) { exception: Throwable ->
                this.catcher?.accept(exception)

                this.specifyCatchers[exception::class.java]?.let { handlers ->
                    for (handler in handlers) {
                        handler.accept(exception.doCast())
                    }
                }

                true
            }
        }

        this.finalizer?.let { finalizer ->
            finalizer(metadata)
        }

        return result
    }

    fun catching(handler: Consumer<Throwable>) {
        this.catcher = handler
    }

    fun catching(targetException: Class<out Throwable>, handler: Consumer<out Throwable>) {
        this.specifyCatchers.computeIfAbsent(targetException) {
            return@computeIfAbsent CollectionFactor.arrayList()
        }.add(handler)
    }

    fun finalize(handler: M.() -> Unit) {
        this.finalizer = handler
    }

    fun then(next: ConiumDSLEventContext<I, N, *, *>.() -> Unit) {
        onEvent(this.event.nextEvent().doCast(), next)
    }
}

