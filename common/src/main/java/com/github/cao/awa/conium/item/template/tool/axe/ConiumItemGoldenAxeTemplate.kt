package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.GOLDEN_AXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemGoldenAxeTemplate : ConiumItemAxeTemplate(
    ToolMaterial.GOLD,
    6.0F,
    -3.0F,
    name = GOLDEN_AXE
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemGoldenAxeTemplate = element.createIfJsonObject(::ConiumItemGoldenAxeTemplate, notSupported())!!
    }
}
