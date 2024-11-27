package com.github.cao.awa.conium.item.template.glint

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Item.GLINT
import com.google.gson.JsonElement
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumGlintTemplate(private val glint: Boolean) : ConiumItemTemplate(name = GLINT) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumGlintTemplate = ConiumGlintTemplate(element.asBoolean)
    }

    override fun settings(settings: Item.Settings) {
        // Set glint override.
        settings.component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, this.glint)
    }
}
