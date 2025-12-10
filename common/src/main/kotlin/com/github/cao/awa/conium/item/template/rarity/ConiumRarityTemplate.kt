package com.github.cao.awa.conium.item.template.rarity

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.item.bedrock.BedrockItemComponents
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates
import com.google.gson.JsonElement
import net.minecraft.item.Item.Settings
import net.minecraft.util.Rarity

open class ConiumRarityTemplate(private val rarity: Rarity, name: String) : ConiumItemTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, name: String = ConiumItemTemplates.RARITY): ConiumRarityTemplate = ConiumRarityTemplate(createRarity(element.asString), name)

        @JvmStatic
        fun createBedrock(element: JsonElement): ConiumRarityTemplate = create(element, BedrockItemComponents.RARITY)
    }

    override fun settings(settings: Settings) {
        // Set rarity.
        settings.rarity(this.rarity)
    }
}
