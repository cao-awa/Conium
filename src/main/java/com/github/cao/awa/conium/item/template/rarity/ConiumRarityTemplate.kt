package com.github.cao.awa.conium.item.template.rarity

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import net.minecraft.item.Item
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.util.Rarity

open class ConiumRarityTemplate(name: String = ConiumTemplates.RARITY, private val rarity: Rarity) : ConiumItemTemplate(name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumRarityTemplate {
            return ConiumRarityTemplate(
                rarity = createRarity(element.asString)
            )
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
