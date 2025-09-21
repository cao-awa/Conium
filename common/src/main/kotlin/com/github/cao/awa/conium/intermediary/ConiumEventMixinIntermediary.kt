package com.github.cao.awa.conium.intermediary

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.handler.ConiumEventHandler
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventType
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.function.Supplier

object ConiumEventMixinIntermediary {
    @JvmStatic
    fun <I : Any, M : ConiumEventMetadata<I>> fireEvent(eventType: ConiumEventType<I, M, *, *>, input: I, argProducer: Consumer<ConiumArisingEventContext<*, *>>) {
        if (eventType.instance().hasListeners()) {
            val context: ConiumArisingEventContext<*, *> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            ConiumEventHandler.execute(context, input)
        } else {
            return
        }
    }

    @JvmStatic
    fun <I : Any, M : ConiumEventMetadata<I>> fireEventIntermediary(eventType: ConiumEventType<I, M, *, *>, input: I, argProducer: Consumer<ConiumArisingEventContext<*, *>>, action: () -> Unit) {
        if (eventType.instance().hasListeners()) {
            val context: ConiumArisingEventContext<*, *> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            ConiumEventHandler.execute(context, input, action)
        } else {
            return
        }
    }

    @JvmStatic
    fun <I : Any, M : ConiumEventMetadata<I>> fireEventCancelable(eventType: ConiumEventType<I, M, *, *>, input: I, argProducer: Consumer<ConiumArisingEventContext<*, *>>): Boolean {
        if (eventType.instance().hasListeners()) {
            val context: ConiumArisingEventContext<*, *> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            return ConiumEventHandler.execute(context, input)
        } else {
            return false
        }
    }

    @JvmStatic
    fun <I : Any, M : ConiumEventMetadata<I>, M2 : ConiumEventMetadata<I>> fireCascadedEvent(
        eventType: ConiumEventType<I, M, *, *>,
        subEventType: ConiumEventType<I, M2, *, *>,
        input: I,
        argProducer: Consumer<ConiumArisingEventContext<*, *>>,
        subArgProducer: Consumer<ConiumArisingEventContext<*, *>>
    ): Boolean {
        if (eventType.instance().hasListeners()) {
            if (fireEventCancelable(eventType, input, argProducer)) {
                return true
            }

            fireEvent(subEventType, input, subArgProducer)
        }
        return false
    }

    @JvmStatic
    fun <I : Any, M : ConiumEventMetadata<I>> fireInheritedCascadedEvent(
        eventType: ConiumEventType<I, M, *, *>,
        subEventType: ConiumEventType<I, M, *, *>,
        input: I,
        argProducer: Consumer<ConiumArisingEventContext<*, *>>,
        subArgProducer: Consumer<ConiumArisingEventContext<*, *>>
    ): Boolean {
        if (eventType.instance().hasListeners()) {
            val context: ConiumArisingEventContext<*, *> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            if (context.presaging(input)) {
                context.arising(input)

                val subContext: ConiumArisingEventContext<*, *> = ConiumEvent.request(subEventType)

                subContext.inherit(context)

                subArgProducer.accept(subContext)

                ConiumEventHandler.execute(subContext, input)

                return false
            }
        }
        return false
    }

    @JvmStatic
    fun <I : Any, M : ConiumEventMetadata<I>, M2 : ConiumEventMetadata<I>, R : Any> fireInheritedCascadedResultEvent(
        eventType: ConiumEventType<I, M, *, *>,
        subEventType: ConiumEventType<I, M2, *, *>,
        input: I,
        argProducer: Consumer<ConiumArisingEventContext<*, *>>,
        subArgProducer: BiConsumer<R, ConiumArisingEventContext<*, *>>,
        resultProducer: Supplier<R>,
        defaultResult: R
    ): R {
        if (!eventType.instance().hasListeners()) {

            val context: ConiumArisingEventContext<*, *> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            return ConiumEventHandler.executeWithPresaging(context, input, defaultResult) {
                val result: R = resultProducer.get()

                val subContext: ConiumArisingEventContext<*, *> = ConiumEvent.request(subEventType)

                subContext.inherit(context)

                subArgProducer.accept(result, subContext)

                ConiumEventHandler.executeWithPresaging(subContext, input, defaultResult) {
                    result
                }
            }
        } else {
            return defaultResult
        }
    }
}