package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.WOODEN_PICKAXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper

class ConiumItemWoodenPickaxeTemplate : ConiumItemPickaxeTemplate(
    ToolMaterial.WOOD,
    attackDamage = 1.0F,
    attackSpeed = -2.8F,
    name = WOODEN_PICKAXE
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumItemWoodenPickaxeTemplate = element.createIfJsonObject(::ConiumItemWoodenPickaxeTemplate, notSupported())!!
    }
}
