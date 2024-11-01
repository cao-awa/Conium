package com.github.cao.awa.conium.item.template.rarity.epic

import com.github.cao.awa.conium.item.template.rarity.ConiumRarityTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.util.Rarity

class ConiumRareRarityTemplate : ConiumRarityTemplate(ConiumTemplates.RARE_RARITY, Rarity.RARE) {
    companion object {
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumRareRarityTemplate {
            if (element.isJsonObject) {
                return ConiumRareRarityTemplate()
            }
            throw IllegalArgumentException("Not supported syntax: $element")
        }
    }
}