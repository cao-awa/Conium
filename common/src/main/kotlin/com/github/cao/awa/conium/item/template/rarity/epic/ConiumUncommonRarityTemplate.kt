package com.github.cao.awa.conium.item.template.rarity.epic

import com.github.cao.awa.conium.item.template.rarity.ConiumRarityTemplate
import com.github.cao.awa.conium.kotlin.extend.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.UNCOMMON_RARITY
import com.google.gson.JsonElement
import net.minecraft.util.Rarity

class ConiumUncommonRarityTemplate : ConiumRarityTemplate(Rarity.UNCOMMON, UNCOMMON_RARITY) {
    companion object {
        fun create(element: JsonElement): ConiumUncommonRarityTemplate = element.createIfJsonObject(::ConiumUncommonRarityTemplate, notSupported())!!
    }
}