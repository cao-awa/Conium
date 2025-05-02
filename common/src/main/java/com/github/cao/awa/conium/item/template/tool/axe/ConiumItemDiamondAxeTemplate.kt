package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.DIAMOND_AXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper

class ConiumItemDiamondAxeTemplate : ConiumItemAxeTemplate(
    ToolMaterial.DIAMOND,
    5.0F,
    -3.0F,
    name = DIAMOND_AXE,
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumItemDiamondAxeTemplate = element.createIfJsonObject(::ConiumItemDiamondAxeTemplate, notSupported())!!
    }
}
