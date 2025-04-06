package com.github.cao.awa.conium.intermediary.mixin

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.type.ConiumEventType
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.function.Supplier

class ConiumEventMixinIntermediary {
    companion object {
        @JvmStatic
        fun <I> fireEvent(eventType: ConiumEventType<I & Any>, input: I & Any, argProducer: Consumer<ConiumArisingEventContext<*>>) {
            val context: ConiumArisingEventContext<*> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            if (context.presaging(input)) {
                context.arising(input)
            }
        }

        @JvmStatic
        fun <I> fireEventIntermediary(eventType: ConiumEventType<I & Any>, input: I & Any, argProducer: Consumer<ConiumArisingEventContext<*>>, action: () -> Unit) {
            val context: ConiumArisingEventContext<*> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            if (context.presaging(input)) {
                action()

                context.arising(input)
            }
        }

        @JvmStatic
        fun <I> fireEventCancelable(eventType: ConiumEventType<I & Any>, input: I & Any, argProducer: Consumer<ConiumArisingEventContext<*>>): Boolean {
            val context: ConiumArisingEventContext<*> = ConiumEvent.request(eventType)

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
            argProducer: Consumer<ConiumArisingEventContext<*>>,
            subArgProducer: Consumer<ConiumArisingEventContext<*>>
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
            argProducer: Consumer<ConiumArisingEventContext<*>>,
            subArgProducer: Consumer<ConiumArisingEventContext<*>>
        ): Boolean {
            val context: ConiumArisingEventContext<*> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            if (context.presaging(input)) {
                context.arising(input)

                val subContext: ConiumArisingEventContext<*> = ConiumEvent.request(subEventType)

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
            argProducer: Consumer<ConiumArisingEventContext<*>>,
            subArgProducer: BiConsumer<R, ConiumArisingEventContext<*>>,
            resultProducer: Supplier<R>,
            defaultResult: R
        ): R {
            val context: ConiumArisingEventContext<*> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            if (context.presaging(input)) {
                context.arising(input)

                val result: R = resultProducer.get()

                val subContext: ConiumArisingEventContext<*> = ConiumEvent.request(subEventType)

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