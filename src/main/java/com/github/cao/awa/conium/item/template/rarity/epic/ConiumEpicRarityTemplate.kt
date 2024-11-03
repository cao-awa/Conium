package com.github.cao.awa.conium.item.template.rarity.epic

import com.github.cao.awa.conium.item.template.rarity.ConiumRarityTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Item.EPIC_RARITY
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.util.Rarity

class ConiumEpicRarityTemplate : ConiumRarityTemplate(EPIC_RARITY, Rarity.EPIC) {
    companion object {
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumEpicRarityTemplate {
            if (element.isJsonObject) {
                return ConiumEpicRarityTemplate()
            }
            throw IllegalArgumentException("Not supported syntax: $element")
        }
    }
}