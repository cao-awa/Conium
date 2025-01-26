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
        if (this.emitsRedstonePower > 0) {
            settings.emitsRedstonePower = true
            settings.redstoneStrongPowerProvider = { _, _, _, _ -> this.emitsRedstonePower }
        }
    }
}
