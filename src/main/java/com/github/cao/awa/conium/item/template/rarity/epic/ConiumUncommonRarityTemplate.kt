package com.github.cao.awa.conium.item.template.rarity.epic

import com.github.cao.awa.conium.item.template.rarity.ConiumRarityTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.util.Rarity

class ConiumUncommonRarityTemplate : ConiumRarityTemplate(ConiumTemplates.UNCOMMON_RARITY, Rarity.UNCOMMON) {
    companion object {
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumUncommonRarityTemplate {
            if (element.isJsonObject) {
                return ConiumUncommonRarityTemplate()
            }
            throw IllegalArgumentException("Not supported syntax: $element")
        }
    }
}