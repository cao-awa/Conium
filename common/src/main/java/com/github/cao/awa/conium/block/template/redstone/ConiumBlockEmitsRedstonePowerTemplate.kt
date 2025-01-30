package com.github.cao.awa.conium.block.template.redstone

import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifBoolean
import com.github.cao.awa.conium.kotlin.extent.json.ifInt
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumBlockEmitsRedstonePowerTemplate(private val emitsRedstonePower: Int): ConiumBlockTemplate(name = ConiumTemplates.Block.EMITS_REDSTONE_POWER) {
    companion object {
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumBlockEmitsRedstonePowerTemplate = element.ifInt(
            ::ConiumBlockEmitsRedstonePowerTemplate
        ) {
            it.ifBoolean(
                { enablePower ->
                    ConiumBlockEmitsRedstonePowerTemplate(when(enablePower) {
                        true -> 0
                        false -> -1
                    })
                },
                notSupported()
            )
        }!!
    }

    override fun settings(settings: ConiumBlockSettings) {
        settings.emitsRedstonePower = this.emitsRedstonePower != -1
        // Only setting power emits when enabled emits redstone power.
        if (settings.emitsRedstonePower) {
            // Setting weak and strong power.
            settings.redstoneWeakPowerProvider = { _, _, _, _ -> this.emitsRedstonePower }
            settings.redstoneStrongPowerProvider = { _, _, _, _ -> this.emitsRedstonePower }
        }
    }
}
