package com.github.cao.awa.conium.item.template.stack.count

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import net.minecraft.item.Item
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumStackMaxCountTemplate(private val stackMaxCount: Int) : ConiumItemTemplate(ConiumTemplates.STACK_MAX_COUNT) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumStackMaxCountTemplate {
            return ConiumStackMaxCountTemplate(validateStackSize(element.asInt))
        }
    }

    override fun settings(settings: Item.Settings) {
        // Set stack max count.
        settings.maxCount(this.stackMaxCount)
    }
}
