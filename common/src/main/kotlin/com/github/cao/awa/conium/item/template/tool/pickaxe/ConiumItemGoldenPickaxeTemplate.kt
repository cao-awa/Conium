package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.kotlin.extend.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.GOLDEN_PICKAXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial

class ConiumItemGoldenPickaxeTemplate : ConiumItemPickaxeTemplate(
    ToolMaterial.GOLD,
    attackDamage = 1.0F,
    attackSpeed = -2.8F,
    name = GOLDEN_PICKAXE
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumItemGoldenPickaxeTemplate = element.createIfJsonObject(::ConiumItemGoldenPickaxeTemplate, notSupported())!!
    }
}
