package com.github.cao.awa.conium.block.template.redstone

import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifInt
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumBlockEmitsStrongRedstonePowerTemplate(private val emitsRedstonePower: Int): ConiumBlockTemplate(name = ConiumTemplates.Block.EMITS_REDSTONE_POWER) {
    companion object {
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBlockEmitsStrongRedstonePowerTemplate = element.ifInt(
            ::ConiumBlockEmitsStrongRedstonePowerTemplate,
            notSupported()
        )!!
    }

    override fun settings(settings: ConiumBlockSettings) {
        // Only setting power emits when power is more than 0.
        if (this.emitsRedstonePower > 0) {
            // Enable redstone power emits.
            settings.emitsRedstonePower = true
            // Setting redstone power.
            settings.redstoneStrongPowerProvider = { _, _, _, _ -> this.emitsRedstonePower }
        }
    }
}
