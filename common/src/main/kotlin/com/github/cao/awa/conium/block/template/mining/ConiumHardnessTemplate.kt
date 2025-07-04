package com.github.cao.awa.conium.block.template.mining

import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.Block.HARDNESS
import com.google.gson.JsonElement
import net.minecraft.block.AbstractBlock
import net.minecraft.registry.RegistryWrapper

class ConiumHardnessTemplate(private val hardness: Float, name: String = HARDNESS) : ConiumBlockTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumHardnessTemplate = ConiumHardnessTemplate(element.asFloat)
    }

    override fun settings(settings: AbstractBlock.Settings) {
        // Set block hardness.
        settings.hardness(this.hardness)
    }
}
