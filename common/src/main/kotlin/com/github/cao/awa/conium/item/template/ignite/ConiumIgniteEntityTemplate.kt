package com.github.cao.awa.conium.item.template.ignite

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.exception.Exceptions.illegalArgument
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifInt
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates
import com.google.gson.JsonElement

class ConiumIgniteEntityTemplate(private val duration: Int) : ConiumItemTemplate(name = ConiumItemTemplates.IGNITE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumIgniteEntityTemplate = element.ifInt(::ConiumIgniteEntityTemplate) {
            it.ifJsonObject(
                { _ ->
                    ConiumIgniteEntityTemplate(100)
                }
            ) {
                illegalArgument("Ignite entity must define an integer duration or using default value, unable to process: $it")
            }
        }!!
    }

    override fun attach(target: ConiumItem) {
        ConiumEvent.itemUseOnEntity.subscribe(target) { _, entity, _, _ ->
            if (entity.fireTicks == 0) {
                entity.fireTicks = this.duration
            }

            true
        }
    }
}
