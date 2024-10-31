package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemWoodenAxeTemplate: ConiumItemAxeTemplate(
    ConiumTemplates.WOODEN_AXE,
    ToolMaterial.WOOD,
    6.0F,
    -3.2F
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemWoodenAxeTemplate {
            if (element is JsonObject) {
                return ConiumItemWoodenAxeTemplate()
            }
            throw IllegalArgumentException("Not supported syntax: $element")
        }
    }
}
