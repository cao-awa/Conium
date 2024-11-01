package com.github.cao.awa.conium.item.template.bedrock.rarity

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import net.minecraft.item.Item
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.util.Rarity

class ConiumBedrockRarityTemplate(private val rarity: Rarity) : ConiumItemTemplate(ConiumTemplates.BEDROCK_RARITY) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBedrockRarityTemplate {
            return ConiumBedrockRarityTemplate(createRarity(element.asString))
        }
    }

    override fun attach(item: ConiumItem) {

    }

    override fun complete(item: ConiumItem) {

    }

    override fun settings(settings: Item.Settings) {
        // Set rarity.
        settings.rarity(this.rarity)
    }
}
