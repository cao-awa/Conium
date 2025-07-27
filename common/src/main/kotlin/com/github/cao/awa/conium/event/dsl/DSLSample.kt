package com.github.cao.awa.conium.event.dsl

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.event.metadata.ConiumEventMetadata
import com.github.cao.awa.conium.event.type.ConiumEventArgTypes
import com.github.cao.awa.conium.event.type.ConiumEventType
import com.github.cao.awa.conium.script.index.common.ConiumEventContextBuilder
import com.github.cao.awa.conium.script.index.common.request
import kotlin.comparisons.then
import kotlin.printStackTrace

object DSLSample {
    fun doDslTest() {
        ConiumDSLEventContext.onEvent(ConiumEventType.ITEM_USE_ON_BLOCK) {
            this.async = true

            action {
                println(this.itemUsageContext.stack)
                println("item use triggered")
                true
            }

            catching { exception ->
                exception.printStackTrace()
            }

            catching(NullPointerException::class.java) { exception ->
                println("NullPointerException happening!")
                exception.printStackTrace()
            }

            finalize {
                println("DSL Event completed execute!")
            }
        }.then {
            action {
                println(this.itemUsageContext.player)
                println("item used event triggered")
                true
            }
        }
    }
}