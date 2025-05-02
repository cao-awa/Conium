package com.github.cao.awa.conium.item.template.rarity.epic

import com.github.cao.awa.conium.item.template.rarity.ConiumRarityTemplate
import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.RARE_RARITY
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Rarity

class ConiumRareRarityTemplate : ConiumRarityTemplate(Rarity.RARE, RARE_RARITY) {
    companion object {
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumRareRarityTemplate = element.createIfJsonObject(::ConiumRareRarityTemplate, notSupported())!!
    }
}