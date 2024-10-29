package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemAxeTemplate
import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemDiamondAxeTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ToolMaterials
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemDiamondPickaxeTemplate: ConiumItemAxeTemplate(ConiumTemplates.DIAMOND_PICKAXE, ToolMaterials.DIAMOND) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemDiamondAxeTemplate {
            if (element is JsonObject) {
                return ConiumItemDiamondAxeTemplate()
            }
            throw IllegalArgumentException("Not supported syntax: $element")
        }
    }
}
