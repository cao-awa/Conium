package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.DIAMOND_AXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial

class ConiumItemDiamondAxeTemplate : ConiumItemAxeTemplate(
    ToolMaterial.DIAMOND,
    attackDamage = 5.0F,
    attackSpeed = -3.0F,
    name = DIAMOND_AXE,
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumItemDiamondAxeTemplate = element.createIfJsonObject(::ConiumItemDiamondAxeTemplate, notSupported())!!
    }
}
