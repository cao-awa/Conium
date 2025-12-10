package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.NETHERITE_AXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial

class ConiumItemNetheriteAxeTemplate : ConiumItemAxeTemplate(
    ToolMaterial.NETHERITE,
    attackDamage = 5F,
    attackSpeed = -3.0F,
    name = NETHERITE_AXE
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumItemNetheriteAxeTemplate = element.createIfJsonObject(::ConiumItemNetheriteAxeTemplate, notSupported())!!
    }
}
