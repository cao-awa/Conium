package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.STONE_PICKAXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemStonePickaxeTemplate : ConiumItemPickaxeTemplate(
    STONE_PICKAXE,
    ToolMaterial.STONE
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemStonePickaxeTemplate = element.createIfJsonObject(::ConiumItemStonePickaxeTemplate, notSupported())!!
    }
}
