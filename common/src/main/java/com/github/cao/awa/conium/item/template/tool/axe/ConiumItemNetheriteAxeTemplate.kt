package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.NETHERITE_AXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper

class ConiumItemNetheriteAxeTemplate : ConiumItemAxeTemplate(
    ToolMaterial.NETHERITE,
    5F,
    -3.0F,
    name = NETHERITE_AXE
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumItemNetheriteAxeTemplate = element.createIfJsonObject(::ConiumItemNetheriteAxeTemplate, notSupported())!!
    }
}
