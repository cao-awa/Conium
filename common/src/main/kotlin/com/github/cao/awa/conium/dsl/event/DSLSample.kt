package com.github.cao.awa.conium.dsl.event

import com.github.cao.awa.conium.event.type.ConiumEventType

object DSLSample {
    fun doDslTest() {
        ConiumDSLEventContext.onEvent(ConiumEventType.BREAKING_BLOCK) {
            this.async = true

            action {
                println(this)
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