package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.item.template.tool.pickaxe.ConiumItemStoneAxeTemplate
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ToolMaterials
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemStoneAxeTemplate: ConiumItemAxeTemplate("stone_axe", ToolMaterials.STONE) {
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
