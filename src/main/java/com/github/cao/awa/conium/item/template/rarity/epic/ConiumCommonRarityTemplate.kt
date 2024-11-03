package com.github.cao.awa.conium.item.template.rarity.epic

import com.github.cao.awa.conium.item.template.rarity.ConiumRarityTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Item.COMMON_RARITY
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.util.Rarity

class ConiumCommonRarityTemplate : ConiumRarityTemplate(COMMON_RARITY, Rarity.COMMON) {
    companion object {
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumCommonRarityTemplate {
            if (element.isJsonObject) {
                return ConiumCommonRarityTemplate()
            }
            throw IllegalArgumentException("Not supported syntax: $element")
        }
    }
}