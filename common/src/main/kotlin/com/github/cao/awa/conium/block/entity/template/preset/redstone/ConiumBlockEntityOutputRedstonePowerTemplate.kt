package com.github.cao.awa.conium.block.entity.template.preset.redstone

import com.github.cao.awa.conium.block.entity.setting.ConiumBlockEntitySettings
import com.github.cao.awa.conium.block.entity.template.ConiumBlockEntityTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper

class ConiumBlockEntityOutputRedstonePowerTemplate: ConiumBlockEntityTemplate(name = ConiumTemplates.BlockEntity.OUTPUT_REDSTONE_POWER) {
    companion object {
        fun create(element: JsonElement): ConiumBlockEntityOutputRedstonePowerTemplate {
            return ConiumBlockEntityOutputRedstonePowerTemplate()
        }
    }

    override fun settings(settings: ConiumBlockEntitySettings) {
        // TODO
    }
}
