package com.github.cao.awa.conium.dsl

import com.github.cao.awa.conium.block.event.breaks.metadata.ConiumBreakBlockEventMetadata
import com.github.cao.awa.conium.block.event.broken.metadata.ConiumBrokenBlockEventMetadata
import com.github.cao.awa.conium.config.ConiumConfig
import com.github.cao.awa.conium.dsl.event.ConiumDSLEventContext
import com.github.cao.awa.conium.dsl.event.ConiumDSLEventContext.Companion.onEvent
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.event.type.cancelable.ConiumCancelableEventType
import com.github.cao.awa.conium.exception.Exceptions.illegalArgument
import com.github.cao.awa.conium.exception.Exceptions.throwIllegalArgument
import net.minecraft.block.Block
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object DSLSample {
    private val LOGGER: Logger = LogManager.getLogger("DSLSample")

    fun doDslTest() {
        // DSL style event handler.
        onEvent(ConiumEventType.BROKEN_BLOCK) {
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
                LOGGER.warn("Destroying block on {}", this.blockPos, this.exception)
            }

            // Catching only NPE to handle,
            catching(NullPointerException::class.java) {
                LOGGER.warn("NullPointerException happening!", this.exception)
            }

            // Finalization action.
            complete {
                LOGGER.info("DSL Event completed execute!")
            }
        }.next {
            action {
                // This next event print '$this' will be
                LOGGER.info("Next context '$this' was triggered after last event '${this.lastContext}'")
                true
            }
        }
    }
}