package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.kotlin.extend.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.STONE_PICKAXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial

class ConiumItemStonePickaxeTemplate : ConiumItemPickaxeTemplate(
    ToolMaterial.STONE,
    name = STONE_PICKAXE,
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumItemStonePickaxeTemplate = element.createIfJsonObject(::ConiumItemStonePickaxeTemplate, notSupported())!!
    }
}
