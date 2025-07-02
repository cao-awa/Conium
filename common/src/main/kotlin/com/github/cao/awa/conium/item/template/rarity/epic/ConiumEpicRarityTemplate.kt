package com.github.cao.awa.conium.item.template.rarity.epic

import com.github.cao.awa.conium.item.template.rarity.ConiumRarityTemplate
import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.EPIC_RARITY
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.Rarity

class ConiumEpicRarityTemplate : ConiumRarityTemplate(Rarity.EPIC, EPIC_RARITY) {
    companion object {
        fun create(element: JsonElement): ConiumEpicRarityTemplate = element.createIfJsonObject(::ConiumEpicRarityTemplate, notSupported())!!
    }
}