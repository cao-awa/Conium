package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ToolMaterials
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemNetheriteAxeTemplate: ConiumItemAxeTemplate(ConiumTemplates.NETHERITE_AXE, ToolMaterials.NETHERITE) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemNetheriteAxeTemplate {
            if (element is JsonObject) {
                return ConiumItemNetheriteAxeTemplate()
            }
            throw IllegalArgumentException("Not supported syntax: $element")
        }
    }
}
