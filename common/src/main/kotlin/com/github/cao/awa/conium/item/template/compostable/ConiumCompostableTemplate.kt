package com.github.cao.awa.conium.item.template.compostable

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.setting.ConiumItemSettings
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.kotlin.extent.json.objectOrFloat
import com.github.cao.awa.conium.template.item.bedrock.BedrockItemComponents.COMPOSTABLE
import com.google.gson.JsonElement
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders

class ConiumCompostableTemplate(private val chance: Float) : ConiumItemTemplate(true, COMPOSTABLE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumCompostableTemplate = element.objectOrFloat(
            {
                // Bedrock schema is:
                // "minecraft:compostable": {
                //     "value": <float>
                // }
                ConiumCompostableTemplate(it["chance"].asFloat)
            }
        ) {
            // Conium additional supporting schema:
            // "minecraft:compostable": <float>
            ConiumCompostableTemplate(it)
        }!!
    }

    override fun prepare(settings: ConiumItemSettings) {
        val providerKey = when {
            this.chance <= 0.35f -> ContextIntProviders.COMPOSTABLE_LOW
            this.chance <= 0.55f -> ContextIntProviders.COMPOSTABLE_LOW_MEDIUM
            this.chance <= 0.75f -> ContextIntProviders.COMPOSTABLE_MEDIUM
            this.chance <= 0.9f -> ContextIntProviders.COMPOSTABLE_MEDIUM_HIGH
            else -> ContextIntProviders.COMPOSTABLE_ALWAYS_ADD_ONE
        }
        settings.vanillaSettings.compostable(providerKey)
    }

    override fun complete(target: ConiumItem) {
    }
}
