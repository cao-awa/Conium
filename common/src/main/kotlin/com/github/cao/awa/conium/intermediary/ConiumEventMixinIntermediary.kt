package com.github.cao.awa.conium.intermediary

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.handler.ConiumEventHandler
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventType
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.function.Supplier

class ConiumEventMixinIntermediary {
    companion object {
        @JvmStatic
        fun <I: Any, M: ConiumEventMetadata<I>> fireEvent(eventType: ConiumEventType<I, M>, input: I, argProducer: Consumer<ConiumArisingEventContext<*, *>>) {
            val context: ConiumArisingEventContext<*, *> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            ConiumEventHandler.execute(context, input)
        }

        @JvmStatic
        fun <I: Any, M: ConiumEventMetadata<I>> fireEventIntermediary(eventType: ConiumEventType<I, M>, input: I, argProducer: Consumer<ConiumArisingEventContext<*, *>>, action: () -> Unit) {
            val context: ConiumArisingEventContext<*, *> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            ConiumEventHandler.execute(context, input, action)
        }

        @JvmStatic
        fun <I: Any, M: ConiumEventMetadata<I>> fireEventCancelable(eventType: ConiumEventType<I, M>, input: I, argProducer: Consumer<ConiumArisingEventContext<*, *>>): Boolean {
            val context: ConiumArisingEventContext<*, *> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            return ConiumEventHandler.execute(context, input)
        }

        @JvmStatic
        fun <I: Any, M: ConiumEventMetadata<I>, M2: ConiumEventMetadata<I>> fireCascadedEvent(
            eventType: ConiumEventType<I, M>,
            subEventType: ConiumEventType<I, M2>,
            input: I,
            argProducer: Consumer<ConiumArisingEventContext<*, *>>,
            subArgProducer: Consumer<ConiumArisingEventContext<*, *>>
        ): Boolean {
            if (fireEventCancelable(eventType, input, argProducer)) {
                return true
            }

            fireEvent(subEventType, input, subArgProducer)

            return false
        }

        @JvmStatic
        fun <I: Any, M: ConiumEventMetadata<I>> fireInheritedCascadedEvent(
            eventType: ConiumEventType<I, M>,
            subEventType: ConiumEventType<I, M>,
            input: I,
            argProducer: Consumer<ConiumArisingEventContext<*, *>>,
            subArgProducer: Consumer<ConiumArisingEventContext<*, *>>
        ): Boolean {
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

            return false
        }

        @JvmStatic
        fun <I: Any, M: ConiumEventMetadata<I>, M2: ConiumEventMetadata<I>, R: Any> fireInheritedCascadedResultEvent(
            eventType: ConiumEventType<I, M>,
            subEventType: ConiumEventType<I, M2>,
            input: I,
            argProducer: Consumer<ConiumArisingEventContext<*, *>>,
            subArgProducer: BiConsumer<R, ConiumArisingEventContext<*, *>>,
            resultProducer: Supplier<R>,
            defaultResult: R
        ): R {
            val context: ConiumArisingEventContext<*, *> = ConiumEvent.request(eventType)

            argProducer.accept(context)

            return ConiumEventHandler.executeWithPresaging(context, input, defaultResult) {
                val result: R = resultProducer.get()

                val subContext: ConiumArisingEventContext<*, *> = ConiumEvent.request(subEventType)

                subContext.inherit(context)

                subArgProducer.accept(result, subContext)

                return@executeWithPresaging ConiumEventHandler.executeWithPresaging(subContext, input,defaultResult) {
                    result
                }
            }
        }
    }
}