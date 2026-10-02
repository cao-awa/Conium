package com.github.cao.awa.conium.block.template.map

import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.kotlin.extent.block.parseAndFindColor
import com.github.cao.awa.conium.template.block.conium.ConiumBlockTemplates.MAP_COLOR
import com.google.gson.JsonElement
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor

class ConiumMapColorTemplate(private val color: MapColor, name: String = MAP_COLOR) : ConiumBlockTemplate(name = name) {
    companion object {
        // Not completed supports.
        @JvmStatic
        fun create(element: JsonElement): ConiumMapColorTemplate = ConiumMapColorTemplate(parseAndFindColor(element.asString))
    }

    override fun settings(settings: BlockBehaviour.Properties) {
        // Set explosion resistance.
        settings.mapColor(this.color)
    }
}
