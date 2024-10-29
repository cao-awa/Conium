package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemAxeTemplate
import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemStoneAxeTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ToolMaterials
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemStonePickaxeTemplate: ConiumItemAxeTemplate(ConiumTemplates.STONE_PICKAXE, ToolMaterials.STONE) {
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