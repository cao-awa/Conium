package com.github.cao.awa.conium.item.template.stack.count

import com.github.cao.awa.conium.item.setting.ConiumItemSettings
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.MAX_COUNT
import com.google.gson.JsonElement
import net.minecraft.world.item.Item

class ConiumStackMaxCountTemplate(private val stackMaxCount: Int) : ConiumItemTemplate(name = MAX_COUNT) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumStackMaxCountTemplate = ConiumStackMaxCountTemplate(validateStackSize(element.asInt))
    }

    override fun settings(settings: ConiumItemSettings) {
        // Record the requested stack size, the final settings resolution has to know it
        // to warn about the conflict with durability.
        settings.requestedMaxStackSize = this.stackMaxCount
    }

    override fun settings(settings: Item.Properties) {
        // Set stack max count.
        settings.stacksTo(this.stackMaxCount)
    }
}
