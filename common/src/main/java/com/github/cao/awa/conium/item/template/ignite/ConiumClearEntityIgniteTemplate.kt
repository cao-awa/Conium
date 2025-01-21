package com.github.cao.awa.conium.item.template.ignite

import com.github.cao.awa.conium.event.ConiumEvent
import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper

class ConiumClearEntityIgniteTemplate(private val isClear: Boolean) : ConiumItemTemplate(
    name = ConiumTemplates.Item.IGNITE,
    conflicts = CONFLICTS
) {
    companion object {
        private val CONFLICTS: Map<Class<out ConiumTemplate<*, *>>, String> = CollectionFactor.hashMap<Class<out ConiumTemplate<*, *>>, String>().also {
            it[ConiumIgniteEntityTemplate::class.java] = "The template 'clear_ignite' are conflicts to 'ignite', the 'ignite' already force removed"
        }

        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumClearEntityIgniteTemplate = ConiumClearEntityIgniteTemplate(element.asBoolean)
    }

    override fun attach(target: ConiumItem) {
        // If not clear, then it is default behaviors, do not register useless context.
        if (this.isClear) {
            ConiumEvent.itemUseOnEntityEvent.subscribe(target) { _, entity, _, _ ->
                entity.fireTicks = 0

                true
            }
        }
    }
}
