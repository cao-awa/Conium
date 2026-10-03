package com.github.cao.awa.conium.item.template.enchantable

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.ENCHANTABLE
import com.google.gson.JsonElement
import net.minecraft.world.item.Item

open class ConiumEnchantableTemplate(private val value: Int, name: String = ENCHANTABLE) : ConiumItemTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumEnchantableTemplate {
            val enchantability = if (element.isJsonPrimitive) {
                element.asInt
            } else if (element.isJsonObject) {
                element.asJsonObject.get("value")?.asInt ?: 1
            } else {
                1
            }
            return ConiumEnchantableTemplate(enchantability)
        }
    }

    override fun settings(settings: Item.Properties) {
        if (this.value > 0) {
            settings.enchantable(this.value)
        }
    }
}
