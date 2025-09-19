package com.github.cao.awa.conium.event.handler

import com.github.cao.awa.conium.event.context.arising.ConiumArisingEventContext
import com.github.cao.awa.conium.threadpool.ConiumThreadPool

class ConiumEventHandler {
    companion object {
        fun <I: Any> execute(context: ConiumArisingEventContext<*, *>, input: I): Boolean {
            return execute(context, input) { }
        }

        fun <I: Any> execute(context: ConiumArisingEventContext<*, *>, input: I, action: () -> Unit): Boolean {
            if (context.presaging(input)) {
                if (context.async) {
                    ConiumThreadPool.run {
                        action()

                        context.arising(input)
                    }
                } else {
                    action()

                    return !context.arising(input)
                }
            }

            return false
        }


        fun <I: Any, R: Any> executeWithPresaging(context: ConiumArisingEventContext<*, *>, input: I, defaultValue: R, action: (I) -> R): R {
            if (context.presaging(input)) {
                if (context.async) {
                    ConiumThreadPool.run {
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
