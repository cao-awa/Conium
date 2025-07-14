package com.github.cao.awa.conium.intermediary.mixin

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventType
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.function.Supplier

class ConiumEventMixinIntermediary {
    companion object {
        @JvmStatic
        fun <I: Any, M: ConiumEventMetadata> fireEvent(eventType: ConiumEventType<I, M>, input: I, argProducer: Consumer<ConiumArisingEventContext<*>>) {
            val context: ConiumArisingEventContext<*> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            if (context.presaging(input)) {
                context.arising(input)
            }
        }

        @JvmStatic
        fun <I: Any, M: ConiumEventMetadata> fireEventIntermediary(eventType: ConiumEventType<I, M>, input: I, argProducer: Consumer<ConiumArisingEventContext<*>>, action: () -> Unit) {
            val context: ConiumArisingEventContext<*> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            if (context.presaging(input)) {
                action()

                context.arising(input)
            }
        }

        @JvmStatic
        fun <I: Any, M: ConiumEventMetadata> fireEventCancelable(eventType: ConiumEventType<I, M>, input: I, argProducer: Consumer<ConiumArisingEventContext<*>>): Boolean {
            val context: ConiumArisingEventContext<*> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            if (context.presaging(input)) {
                context.arising(input)

                return false
            }

            return true
        }

        @JvmStatic
        fun <I: Any, M: ConiumEventMetadata, M2: ConiumEventMetadata> fireCascadedEvent(
            eventType: ConiumEventType<I, M>,
            subEventType: ConiumEventType<I, M2>,
            input: I,
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
        fun <I: Any, M: ConiumEventMetadata> fireInheritedCascadedEvent(
            eventType: ConiumEventType<I, M>,
            subEventType: ConiumEventType<I, M>,
            input: I,
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
        fun <I: Any, M: ConiumEventMetadata, M2: ConiumEventMetadata, R> fireInheritedCascadedResultEvent(
            eventType: ConiumEventType<I, M>,
            subEventType: ConiumEventType<I, M2>,
            input: I,
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