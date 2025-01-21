package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.IRON_AXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemIronAxeTemplate : ConiumItemAxeTemplate(
    ToolMaterial.IRON,
    6.0F,
    -3.1F,
    name = IRON_AXE
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemIronAxeTemplate = element.createIfJsonObject(::ConiumItemIronAxeTemplate, notSupported())!!
    }
}
