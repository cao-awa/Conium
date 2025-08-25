package com.github.cao.awa.conium.block.template.map

import com.github.cao.awa.conium.block.template.ConiumBlockTemplate
import com.github.cao.awa.conium.kotlin.extend.block.parseAndFindColor
import com.github.cao.awa.conium.template.ConiumTemplates.Block.MAP_COLOR
import com.google.gson.JsonElement
import net.minecraft.block.AbstractBlock
import net.minecraft.block.MapColor

class ConiumMapColorTemplate(private val color: MapColor, name: String = MAP_COLOR) : ConiumBlockTemplate(name = name) {
    companion object {
        // Not completed supports.
        @JvmStatic
        fun create(element: JsonElement): ConiumMapColorTemplate = ConiumMapColorTemplate(parseAndFindColor(element.asString))
    }

    override fun settings(settings: AbstractBlock.Settings) {
        // Set explosion resistance.
        settings.mapColor(this.color)
    }
}
