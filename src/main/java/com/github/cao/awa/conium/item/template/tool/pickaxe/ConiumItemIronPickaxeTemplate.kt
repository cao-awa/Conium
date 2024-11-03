package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.IRON_PICKAXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemIronPickaxeTemplate : ConiumItemPickaxeTemplate(
    IRON_PICKAXE,
    ToolMaterial.IRON
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemIronPickaxeTemplate = element.createIfJsonObject(::ConiumItemIronPickaxeTemplate) {
            throw IllegalArgumentException("Not supported syntax: $it")
        }!!
    }
}
