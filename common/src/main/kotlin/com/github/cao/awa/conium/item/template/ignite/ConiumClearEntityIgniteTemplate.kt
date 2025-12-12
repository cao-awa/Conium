package com.github.cao.awa.conium.item.template.ignite

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates
import com.google.gson.JsonElement

class ConiumClearEntityIgniteTemplate(private val isClear: Boolean) : ConiumItemTemplate(
    name = ConiumItemTemplates.IGNITE
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumClearEntityIgniteTemplate = ConiumClearEntityIgniteTemplate(element.asBoolean)
    }

    override fun attach(target: ConiumItem) {
        // If not clear, then it is default behaviors, do not register useless context.
        if (this.isClear) {
            ConiumEvent.itemUseOnEntity.subscribe(target) { _, entity, _, _ ->
                if (entity.fireTicks > 0) {
                    entity.fireTicks = 0
                }

                true
            }
        }
    }
}
