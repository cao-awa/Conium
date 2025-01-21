package com.github.cao.awa.conium.block.template.luminance

import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Block.LUMINANCE
import com.google.gson.JsonElement
import net.minecraft.block.AbstractBlock
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumLuminanceTemplate(private val level: Int, name: String = LUMINANCE) : ConiumBlockTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumLuminanceTemplate = ConiumLuminanceTemplate(validateLuminance(element.asInt))

        fun validateLuminance(level: Int): Int {
            if (level in 0..15) {
                return level
            }
            throw IllegalArgumentException("Block luminance must in range 0 to 15")
        }
    }

    override fun settings(settings: AbstractBlock.Settings) {
        settings.luminance { this.level }
    }
}
