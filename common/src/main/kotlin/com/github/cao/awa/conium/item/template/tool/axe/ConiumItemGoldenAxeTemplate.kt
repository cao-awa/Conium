package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.GOLDEN_AXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial

class ConiumItemGoldenAxeTemplate : ConiumItemAxeTemplate(
    ToolMaterial.GOLD,
    attackDamage = 6.0F,
    attackSpeed = -3.0F,
    name = GOLDEN_AXE
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumItemGoldenAxeTemplate = element.createIfJsonObject(::ConiumItemGoldenAxeTemplate, notSupported())!!
    }
}
