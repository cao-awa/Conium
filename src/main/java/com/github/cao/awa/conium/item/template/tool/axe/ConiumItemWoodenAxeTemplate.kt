package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.WOODEN_AXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemWoodenAxeTemplate : ConiumItemAxeTemplate(
    WOODEN_AXE,
    ToolMaterial.WOOD,
    6.0F,
    -3.2F
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemWoodenAxeTemplate = element.createIfJsonObject(::ConiumItemWoodenAxeTemplate) {
            throw IllegalArgumentException("Not supported syntax: $it")
        }!!
    }
}
