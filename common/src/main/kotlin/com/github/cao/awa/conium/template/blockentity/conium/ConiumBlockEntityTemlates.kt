package com.github.cao.awa.conium.template.blockentity.conium

import com.github.cao.awa.conium.blockentity.template.preset.redstone.ConiumBlockEntityOutputRedstonePowerTemplate
import com.github.cao.awa.conium.template.ConiumTemplate

object ConiumBlockEntityTemplates {
    const val OUTPUT_REDSTONE_POWER: String = "output_redstone_power"

    fun initBlockEntityTemplates() {
        ConiumTemplate.registerBlockEntity(
            OUTPUT_REDSTONE_POWER,
            ConiumBlockEntityOutputRedstonePowerTemplate::create
        )
    }
}