package com.github.cao.awa.conium.block.template.mining

import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.template.block.conium.ConiumBlockTemplates.HARDNESS
import com.google.gson.JsonElement
import net.minecraft.world.level.block.state.BlockBehaviour

class ConiumHardnessTemplate(private val hardness: Float, name: String = HARDNESS) : ConiumBlockTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumHardnessTemplate = ConiumHardnessTemplate(element.asFloat)
    }

    override fun settings(settings: BlockBehaviour.Properties) {
        // Set block hardness.
        settings.destroyTime(this.hardness)
    }
}
