package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.template.ConiumTemplates.Item.GOLDEN_AXE
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemGoldenAxeTemplate: ConiumItemAxeTemplate(
    GOLDEN_AXE,
    ToolMaterial.GOLD,
    6.0F,
    -3.0F
) {
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
