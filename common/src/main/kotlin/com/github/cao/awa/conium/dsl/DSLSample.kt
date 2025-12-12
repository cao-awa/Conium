package com.github.cao.awa.conium.dsl

import com.github.cao.awa.conium.config.ConiumConfig
import com.github.cao.awa.conium.dsl.event.ConiumDSLEventContext.Companion.onEvent
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.exception.Exceptions.throwIllegalArgument
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object DSLSample {
    private val LOGGER: Logger = LogManager.getLogger("DSLSample")

    fun doDslTest() {
        // DSL style event handler.
        onEvent(ConiumEventType.BREAK_BLOCK) {
            this.async = true

            // Event processing logic.
            action {
                LOGGER.info(this.blockPos)
                if (ConiumConfig.debugs) {
                    throwIllegalArgument("test")
                }

                true
            }

            // Catching all exception to handle.
            catching {
                // Catching block can get current context data to analyze the problems at.
                LOGGER.warn("Destroying block on {}", this.blockPos, this.exception)
            }

            // Catching only NPE to handle,
            catching(NullPointerException::class.java) {
                LOGGER.warn("NullPointerException happening!", this.exception)
            }

            // Finalization action.
            complete {
                // Complete block also can get the context data.
                LOGGER.info("DSL event handled block event at pos {}", this.blockPos)
                LOGGER.info("DSL event completed execute!")
            }
        }.next {
            action {
                // This next event print '$this' will be current context, and '${this.lastContext}' will be last context.
                // In this sample, the last context will be 'BREAK_BLOCK'.
                // And the current context will be 'BROKEN_BLOCK' -
                // because the next event of 'BREAK_BLOCK' is broken block that is specified in the event instance.
                LOGGER.info("Next context '$this' was triggered after last event '${this.lastContext}'")
                true
            }
        }
    }
}