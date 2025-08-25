package com.github.cao.awa.conium.dsl

import com.github.cao.awa.conium.config.ConiumConfig
import com.github.cao.awa.conium.dsl.event.ConiumDSLEventContext
import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.type.ConiumEventType

object DSLSample {
    fun doDslTest() {
        ConiumEvent.breakingBlock.listen {

        }
        ConiumDSLEventContext.onEvent(ConiumEventType.BREAKING_BLOCK) {
            this.async = true

            action {
                println(this.blockPos)
                if (ConiumConfig.debugs) {
                    throw IllegalStateException("Test")
                }
                true
            }

            catching { exception ->
                exception.printStackTrace()
            }

            catching(NullPointerException::class.java) { exception ->
                println("NullPointerException happening!")
                exception.printStackTrace()
            }

            complete {
                println("DSL Event completed execute!")
            }
        }.next {
            action {
                println(this)
                true
            }
        }
    }
}