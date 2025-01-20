package com.github.cao.awa.conium.item.template.ignite

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifInt
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper

class ConiumIgniteEntityTemplate(private val duration: Int) : ConiumItemTemplate(name = ConiumTemplates.Item.IGNITE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumIgniteEntityTemplate = element.ifInt(::ConiumIgniteEntityTemplate) {
            it.ifJsonObject(
                { _ ->
                    ConiumIgniteEntityTemplate(100)
                }
            ) {
                throw IllegalArgumentException("Ignite entity must define an integer duration or using default value, unable to process: $it")
            }
        }!!
    }

    override fun attach(target: ConiumItem) {
        ConiumEvent.itemUseOnEntityEvent.subscribe(target) { _, entity, _, _ ->
            entity.fireTicks = this.duration

            true
        }
    }
}
