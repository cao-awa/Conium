package com.github.cao.awa.conium.event.handler;

import com.github.cao.awa.conium.event.context.ConiumEventContext
import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.threadpool.ConiumThreadPool
import java.util.function.Function
import java.util.function.Supplier

class ConiumEventHandler {
    companion object {
        fun <I: Any> execute(context: ConiumArisingEventContext<*, *>, input: I): Boolean {
            if (context.presaging(input)) {
                if (context.async) {
                    ConiumThreadPool.execute {
                        context.arising(input)
                    }
                } else {
                    context.arising(input)
                }
                return false
            }

            return true
        }

        fun <I: Any> executeWithMiddleAction(context: ConiumArisingEventContext<*, *>, input: I, action: () -> Unit): Boolean {
            if (context.presaging(input)) {
                if (context.async) {
                    ConiumThreadPool.execute {
                        action()

                        context.arising(input)
                    }
                } else {
                    action()

                    context.arising(input)
                }
                return false
            }

            return true
        }


        fun <I: Any, R: Any> executeWithPresaging(context: ConiumArisingEventContext<*, *>, input: I, defaultValue: R, action: (I) -> R): R {
            if (context.presaging(input)) {
                if (context.async) {
                    ConiumThreadPool.execute {
                        context.arising(input)
                    }
                } else {
                    context.arising(input)
                }
                return action(input)
            }

            return defaultValue
        }
    }
}
