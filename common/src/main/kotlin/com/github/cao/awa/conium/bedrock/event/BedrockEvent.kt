@file:Suppress("unused")

package com.github.cao.awa.conium.bedrock.event

import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApi
import com.github.cao.awa.conium.annotation.bedrock.BedrockScriptApiFacade
import com.github.cao.awa.conium.bedrock.event.context.BedrockEventContext
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.context.ConiumEventContextBuilder
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.parameter.ParameterSelective1
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import net.minecraft.block.Block

abstract class BedrockEvent<I: Any, E : BedrockEventContext<I>>(private val targetEvent: ConiumEventType<I, out ConiumEventMetadata<I>>) {
    private val subscribers: MutableList<ConiumArisingEventContext<*, *>> = CollectionFactor.arrayList()

    /**
     * Init the bedrock event instance, attaching to target conium event and trigger the subscribers.
     *
     * @author cao_awa
     * @author 草二号机
     *
     * @since 1.0.0
     */
    init {
        // Use unnamed context attaching.
        val context: ConiumArisingEventContext<*, ParameterSelective1<Boolean, Any>> = ConiumEventContextBuilder.unnamed()

        // Attach bedrock event instance to conium event.
        ConiumEvent.forever(
            this.targetEvent,
            context.presage { identity: Any ->
                // Process all subscribers.
                this.subscribers.map { subscriber: ConiumArisingEventContext<*, *> ->
                    // Let subscriber inherit the event context from the conium event system.
                    subscriber.inherit(context)

                    // Trigger subscribe arising.
                    // The unnamed context also used dynamic args to transform args.
                    // For more details, read some 'createUnnamed' implements.
                    subscriber.arising(identity)
                }.none { !it }
            }
        )
    }

    @BedrockScriptApi
    @BedrockScriptApiFacade("*EventSignal", "subscribe")
    fun subscribe(action: ParameterSelective1<Unit, E>): ConiumArisingEventContext<*, *> {
        // When subscribe to event, using unnamed context and put to the subscriber list instead of to request conium event.
        // The event cannot unsubscribe if request to conium event because conium event using 'forever' lifecycle for all requesting contexts.
        return createUnnamed(
            action,
            BedrockEventContext.currentPosting!!
        ).also(this.subscribers::add)
    }

    @BedrockScriptApi
    @BedrockScriptApiFacade("*EventSignal", "unsubscribe")
    fun unsubscribe(context: ConiumArisingEventContext<*, *>) = this.subscribers.removeIf { it == context }

    abstract fun createUnnamed(action: ParameterSelective1<Unit, E>, scriptSource: Any): ConiumArisingEventContext<*, *>
}
