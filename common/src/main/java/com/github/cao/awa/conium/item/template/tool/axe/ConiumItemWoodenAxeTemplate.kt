package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.WOODEN_AXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper

class ConiumItemWoodenAxeTemplate : ConiumItemAxeTemplate(
    ToolMaterial.WOOD,
    6.0F,
    -3.2F,
    name = WOODEN_AXE
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumItemWoodenAxeTemplate = element.createIfJsonObject(::ConiumItemWoodenAxeTemplate, notSupported())!!
    }
}
