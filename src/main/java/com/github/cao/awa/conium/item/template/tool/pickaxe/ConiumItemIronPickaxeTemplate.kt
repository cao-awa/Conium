package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemAxeTemplate
import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemIronAxeTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ToolMaterials
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemIronPickaxeTemplate: ConiumItemAxeTemplate(ConiumTemplates.IRON_PICKAXE, ToolMaterials.IRON) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemIronAxeTemplate {
            if (element is JsonObject) {
                return ConiumItemIronAxeTemplate()
            }
            throw IllegalArgumentException("Not supported syntax: $element")
        }
    }
}
