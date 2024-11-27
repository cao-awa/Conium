package com.github.cao.awa.conium.item.template.rarity

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockItem
import com.github.cao.awa.conium.template.ConiumTemplates.Item
import com.google.gson.JsonElement
import net.minecraft.item.Item.Settings
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.util.Rarity

open class ConiumRarityTemplate(private val rarity: Rarity, name: String) : ConiumItemTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup, name: String = Item.RARITY): ConiumRarityTemplate = ConiumRarityTemplate(createRarity(element.asString), name)

        @JvmStatic
        fun createBedrock(element: JsonElement, registryLookup: WrapperLookup): ConiumRarityTemplate = create(element, registryLookup, BedrockItem.RARITY)
    }

    override fun settings(settings: Settings) {
        // Set rarity.
        settings.rarity(this.rarity)
    }
}
