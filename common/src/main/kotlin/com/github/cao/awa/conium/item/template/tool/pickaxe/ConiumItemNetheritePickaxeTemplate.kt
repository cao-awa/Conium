package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.NETHERITE_PICKAXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial

class ConiumItemNetheritePickaxeTemplate : ConiumItemPickaxeTemplate(
    ToolMaterial.NETHERITE,
    name = NETHERITE_PICKAXE
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumItemNetheritePickaxeTemplate = element.createIfJsonObject(::ConiumItemNetheritePickaxeTemplate, notSupported())!!
    }
}
