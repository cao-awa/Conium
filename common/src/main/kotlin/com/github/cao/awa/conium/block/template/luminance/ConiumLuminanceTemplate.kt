package com.github.cao.awa.conium.block.template.luminance

import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.template.block.conium.ConiumBlockTemplates.LUMINANCE
import com.google.gson.JsonElement
import net.minecraft.world.level.block.state.BlockBehaviour

class ConiumLuminanceTemplate(private val level: Int, name: String = LUMINANCE) : ConiumBlockTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumLuminanceTemplate = ConiumLuminanceTemplate(validateLuminance(element.asInt))

        fun validateLuminance(level: Int): Int {
            if (level in 0..15) {
                return level
            }
            throw IllegalArgumentException("Block luminance must in range 0 to 15")
        }
    }

    override fun settings(settings: BlockBehaviour.Properties) {
        settings.lightLevel { this.level }
    }
}
