package com.github.cao.awa.conium.block.template.redstone

import com.github.cao.awa.conium.block.ConiumBlock
import com.github.cao.awa.conium.block.setting.ConiumBlockSettings
import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.kotlin.extent.json.ifInt
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper

/**
 * Setting a block emits strong redstone power to neighbor blocks.
 *
 * @see ConiumBlock.emitsRedstonePower
 * @see ConiumBlock.getStrongRedstonePower
 * @see ConiumBlock.updateDirections
 * @see ConiumBlockSettings
 * @see ConiumBlockEmitsRedstonePowerTemplate
 * @see ConiumBlockEmitsWeakRedstonePowerTemplate
 *
 * @author cao_aw
 *
 * @since 1.0.0
 */
class ConiumBlockEmitsStrongRedstonePowerTemplate(private val emitsRedstonePower: Int): ConiumBlockTemplate(name = ConiumTemplates.Block.EMITS_REDSTONE_POWER) {
    companion object {
        /**
         * Create the template with the emitting power level.
         *
         * @see ConiumBlock.emitsRedstonePower
         * @see ConiumBlock.getStrongRedstonePower
         * @see ConiumBlock.updateDirections
         * @see ConiumBlockSettings
         *
         * @author cao_awa
         *
         * @since 1.0.0
         */
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumBlockEmitsStrongRedstonePowerTemplate {
            // If the value is int, then it should be emitting power level.
            return element.ifInt(
                ::ConiumBlockEmitsStrongRedstonePowerTemplate,
                // The value must be integer, or else it is the wrong type.
                notSupported()
            )!!
        }
    }

    /**
     * Setting the block emits strong redstone power.
     *
     * @see ConiumBlock.emitsRedstonePower
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
            // Setting redstone power.
            settings.redstoneStrongPowerProvider = { _, _, _, _ -> this.emitsRedstonePower }
        }
    }
}
