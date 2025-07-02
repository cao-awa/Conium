package com.github.cao.awa.conium.block.template.redstone

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.entity.ConiumBlockEntity
import com.github.cao.awa.conium.block.entity.setting.ConiumBlockEntitySettings
import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifBoolean
import com.github.cao.awa.conium.kotlin.extent.json.ifInt
import com.github.cao.awa.conium.nbt.data.ConiumNbtDataSerializer
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper

/**
 * Setting a block emits weak and strong redstone power to neighbor blocks.
 *
 * @see ConiumBlock.emitsRedstonePower
 * @see ConiumBlock.getStrongRedstonePower
 * @see ConiumBlock.getWeakRedstonePower
 * @see ConiumBlock.updateDirections
 * @see ConiumBlockSettings
 * @see ConiumBlockEmitsStrongRedstonePowerTemplate
 * @see ConiumBlockEmitsWeakRedstonePowerTemplate
 *
 * @author cao_aw
 *
 * @since 1.0.0
 */
class ConiumBlockEmitsRedstonePowerTemplate(private val emitsRedstonePower: Int) : ConiumBlockTemplate(name = ConiumTemplates.Block.EMITS_REDSTONE_POWER) {
    companion object {
        /**
         * Create the template with the emitting power level or the enabled emits flag.
         *
         * @see ConiumBlock
         * @see ConiumBlockEntity
         * @see ConiumBlockSettings
         * @see ConiumBlockEntitySettings
         * @see ConiumNbtDataSerializer
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        fun create(element: JsonElement): ConiumBlockEmitsRedstonePowerTemplate {
            // If the value is int, then it should be emitting power level.
            return element.ifInt(
                ::ConiumBlockEmitsRedstonePowerTemplate
            ) {
                // Then else is boolean then mean it is enabled emits flag.
                it.ifBoolean(
                    { enablePower ->
                        ConiumBlockEmitsRedstonePowerTemplate(
                            // If not enabled, use -1 to mark.
                            when (enablePower) {
                                true -> 0
                                false -> -1
                            }
                        )
                    },
                    // The value should not be others that not int or boolean, it is the wrong type.
                    notSupported()
                )
            }!!
        }
    }

    /**
     * Setting the block emits weak and strong redstone power.
     *
     * @see ConiumBlock.emitsRedstonePower
     * @see ConiumBlock.getWeakRedstonePower
     * @see ConiumBlock.getStrongRedstonePower
     * @see ConiumBlock.updateDirections
     * @see ConiumBlockSettings
     *
     * @param settings the block settings
     *
     * @author cao_awa
     *
     * @since 1.0.0
     */
    override fun settings(settings: ConiumBlockSettings) {
        // Only setting power emits when enabled emits redstone power.
        if (this.emitsRedstonePower != -1) {
            // Enable redstone power emits.
            settings.emitsRedstonePower = true
            // Setting weak and strong power.
            settings.redstoneWeakPowerProvider = { _, _, _, _ -> this.emitsRedstonePower }
            settings.redstoneStrongPowerProvider = { _, _, _, _ -> this.emitsRedstonePower }
        }
    }
}
