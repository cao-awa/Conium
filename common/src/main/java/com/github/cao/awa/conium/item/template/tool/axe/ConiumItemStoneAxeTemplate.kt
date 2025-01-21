package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.STONE_AXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemStoneAxeTemplate : ConiumItemAxeTemplate(
    ToolMaterial.STONE,
    7.0F,
    -3.2F,
    name = STONE_AXE,
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemStoneAxeTemplate = element.createIfJsonObject(::ConiumItemStoneAxeTemplate, notSupported())!!
    }
}
