package com.github.cao.awa.conium.item.template.stack.count

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.MAX_COUNT
import com.google.gson.JsonElement
import net.minecraft.world.item.Item

class ConiumStackMaxCountTemplate(private val stackMaxCount: Int) : ConiumItemTemplate(name = MAX_COUNT) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumStackMaxCountTemplate = ConiumStackMaxCountTemplate(validateStackSize(element.asInt))
    }

    override fun settings(settings: Item.Properties) {
        // Set stack max count.
        settings.stacksTo(this.stackMaxCount)
    }
}
