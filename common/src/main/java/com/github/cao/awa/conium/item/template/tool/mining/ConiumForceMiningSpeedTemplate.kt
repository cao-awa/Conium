package com.github.cao.awa.conium.item.template.tool.mining

import com.github.cao.awa.conium.item.setting.ConiumItemSettings
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Item
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper

open class ConiumForceMiningSpeedTemplate(private val miningSpeed: Float) : ConiumItemTemplate(name = Item.FORCE_MINING_SPEED) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumForceMiningSpeedTemplate = ConiumForceMiningSpeedTemplate(element.asFloat)
    }

    override fun settings(settings: ConiumItemSettings) {
        // Set mining speed.
        settings.forceMiningSpeed = this.miningSpeed
    }
}
