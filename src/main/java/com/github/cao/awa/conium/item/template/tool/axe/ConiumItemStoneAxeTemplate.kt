package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemStoneAxeTemplate: ConiumItemAxeTemplate(
    ConiumTemplates.STONE_AXE,
    ToolMaterial.STONE,
    7.0F,
    -3.2F
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemStoneAxeTemplate {
            if (element is JsonObject) {
                return ConiumItemStoneAxeTemplate()
            }
            throw IllegalArgumentException("Not supported syntax: $element")
        }
    }
}
