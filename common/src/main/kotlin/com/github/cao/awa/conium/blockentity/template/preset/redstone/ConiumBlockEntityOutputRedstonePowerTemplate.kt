package com.github.cao.awa.conium.blockentity.template.preset.redstone

import com.github.cao.awa.conium.blockentity.setting.ConiumBlockEntitySettings
import com.github.cao.awa.conium.blockentity.template.ConiumBlockEntityTemplate
import com.github.cao.awa.conium.template.blockentity.conium.ConiumBlockEntityTemplates
import com.google.gson.JsonElement

class ConiumBlockEntityOutputRedstonePowerTemplate: ConiumBlockEntityTemplate(name = ConiumBlockEntityTemplates.OUTPUT_REDSTONE_POWER) {
    companion object {
        fun create(element: JsonElement): ConiumBlockEntityOutputRedstonePowerTemplate {
            return ConiumBlockEntityOutputRedstonePowerTemplate()
        }
    }

    override fun settings(settings: ConiumBlockEntitySettings) {
        // TODO
    }
}
