package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemAxeTemplate
import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemNetheriteAxeTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ToolMaterials
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemNetheritePickaxeTemplate: ConiumItemAxeTemplate(ConiumTemplates.NETHERITE_PICKAXE, ToolMaterials.NETHERITE) {
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
