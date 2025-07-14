package com.github.cao.awa.conium.dsl

import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.script.index.common.ConiumEventContextBuilder

class DSLSample {

}

fun main() {
    onEvent {
        target = ConiumEventType.ITEM_USE_ON_BLOCK
        action {
            println(this.itemUsageContext.stack)
            println("awa")
            true
        }

        catching {
            this.exception.printStackTrace()
        }

        finalize {
            println("DSL Event completed execute!")
        }
    }

    onEvent(ConiumEventType.SERVER_TICK) {
        action {
            println(this.server)
            println("qaq")

            transform(ConiumEventArgTypes.SERVER_PLAYER) {
                println(this.recipeBook)
            }

            true
        }
    }

    ConiumEventContextBuilder.request(ConiumEventType.SERVER_TICK) {
        println("???")
        true
    }
}

fun <I: Any, M: ConiumEventMetadata, T: ConiumEventType<I, M>> onEvent(
    eventType: T,
    block: DSLEventMetadata<I, M, T>.() -> Unit
): DSLEventMetadata<I, M, T> = DSLEventMetadata(eventType).also { dslEventMetadata: DSLEventMetadata<I, M, T> ->
    block(dslEventMetadata)
}

fun <I: Any, M: ConiumEventMetadata, T: ConiumEventType<I, M>> onEvent(
    block: DSLEventMetadata<I, M, T>.() -> Unit
): DSLEventMetadata<I, M, T> = DSLEventMetadata<I, M, T>().also { dslEventMetadata: DSLEventMetadata<I, M, T> ->
    block(dslEventMetadata)
}

fun <I: Any, M: ConiumEventMetadata, T: ConiumEventType<I, M>> listen(
    eventType: T,
    block: DSLEventMetadata<I, M, T>.() -> Unit
): DSLEventMetadata<I, M, T> = onEvent(eventType, block)