package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.IRON_PICKAXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial

class ConiumItemIronPickaxeTemplate : ConiumItemPickaxeTemplate(
    ToolMaterial.IRON,
    name = IRON_PICKAXE,
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumItemIronPickaxeTemplate = element.createIfJsonObject(::ConiumItemIronPickaxeTemplate, notSupported())!!
    }
}
