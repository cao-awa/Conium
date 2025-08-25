package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.kotlin.extend.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.NETHERITE_AXE
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
