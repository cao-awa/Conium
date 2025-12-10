package com.github.cao.awa.conium.item.template.tool.mining

import com.github.cao.awa.conium.item.setting.ConiumItemSettings
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates
import com.google.gson.JsonElement

open class ConiumForceMiningSpeedTemplate(private val miningSpeed: Float) : ConiumItemTemplate(name = ConiumItemTemplates.FORCE_MINING_SPEED) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumForceMiningSpeedTemplate = ConiumForceMiningSpeedTemplate(element.asFloat)
    }

    override fun settings(settings: ConiumItemSettings) {
        // Set mining speed.
        settings.forceMiningSpeed = this.miningSpeed
    }
}
