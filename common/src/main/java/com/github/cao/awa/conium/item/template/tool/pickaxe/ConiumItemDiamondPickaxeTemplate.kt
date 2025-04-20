package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.DIAMOND_PICKAXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper

class ConiumItemDiamondPickaxeTemplate : ConiumItemPickaxeTemplate(
    ToolMaterial.DIAMOND,
    name = DIAMOND_PICKAXE,
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumItemDiamondPickaxeTemplate = element.createIfJsonObject(::ConiumItemDiamondPickaxeTemplate, notSupported())!!
    }
}
