package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ToolMaterials
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemGoldenAxeTemplate: ConiumItemAxeTemplate(ConiumTemplates.GOLDEN_AXE, ToolMaterials.GOLD) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemGoldenAxeTemplate {
            if (element is JsonObject) {
                return ConiumItemGoldenAxeTemplate()
            }
            throw IllegalArgumentException("Not supported syntax: $element")
        }
    }
}
