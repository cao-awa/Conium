package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.IRON_AXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial

class ConiumItemIronAxeTemplate : ConiumItemAxeTemplate(
    ToolMaterial.IRON,
    attackDamage = 6.0F,
    attackSpeed = -3.1F,
    name = IRON_AXE
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumItemIronAxeTemplate = element.createIfJsonObject(::ConiumItemIronAxeTemplate, notSupported())!!
    }
}
