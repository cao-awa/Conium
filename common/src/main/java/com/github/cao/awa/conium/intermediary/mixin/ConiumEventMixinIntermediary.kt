package com.github.cao.awa.conium.intermediary.mixin

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.type.ConiumEventType
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.function.Supplier

class ConiumEventMixinIntermediary {
    companion object {
        @JvmStatic
        fun <I : Any> fireEvent(eventType: ConiumEventType<I>, input: I, argProducer: Consumer<ConiumEventContext<*>>) {
            val context: ConiumEventContext<*> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            if (context.presaging(input)) {
                context.arising(input)
            }
        }

        @JvmStatic
        fun <I : Any> fireEventCancelable(eventType: ConiumEventType<I>, input: I, argProducer: Consumer<ConiumEventContext<*>>): Boolean {
            val context: ConiumEventContext<*> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            if (context.presaging(input)) {
                context.arising(input)

                return false
            }

            return true
        }

        @JvmStatic
        fun <I> fireCascadedEvent(
            eventType: ConiumEventType<I & Any>,
            subEventType: ConiumEventType<I & Any>,
            input: I & Any,
            argProducer: Consumer<ConiumEventContext<*>>,
            subArgProducer: Consumer<ConiumEventContext<*>>
        ): Boolean {
            if (fireEventCancelable(eventType, input, argProducer)) {
                return true
            }

            fireEvent(subEventType, input, subArgProducer)

            return false
        }

        @JvmStatic
        fun <I> fireInheritedCascadedEvent(
            eventType: ConiumEventType<I & Any>,
            subEventType: ConiumEventType<I & Any>,
            input: I & Any,
            argProducer: Consumer<ConiumEventContext<*>>,
            subArgProducer: Consumer<ConiumEventContext<*>>
        ): Boolean {
            val context: ConiumEventContext<*> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            if (context.presaging(input)) {
                context.arising(input)

                val subContext: ConiumEventContext<*> = ConiumEvent.request(subEventType)

                subContext.inherit(context)

                subArgProducer.accept(subContext)

                if (subContext.presaging(input)) {
                    subContext.arising(input)
                }

                return false
            }

            return false
        }

        @JvmStatic
        fun <I, R> fireInheritedCascadedResultEvent(
            eventType: ConiumEventType<I & Any>,
            subEventType: ConiumEventType<I & Any>,
            input: I & Any,
            argProducer: Consumer<ConiumEventContext<*>>,
            subArgProducer: BiConsumer<R, ConiumEventContext<*>>,
            resultProducer: Supplier<R>,
            defaultResult: R
        ): R {
            val context: ConiumEventContext<*> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            if (context.presaging(input)) {
                context.arising(input)

                val result: R = resultProducer.get()

                val subContext: ConiumEventContext<*> = ConiumEvent.request(subEventType)

                subContext.inherit(context)

                subArgProducer.accept(result, subContext)

                if (subContext.presaging(input)) {
                    subContext.arising(input)

                    return result
                }
            }

            return defaultResult
        }
    }
}