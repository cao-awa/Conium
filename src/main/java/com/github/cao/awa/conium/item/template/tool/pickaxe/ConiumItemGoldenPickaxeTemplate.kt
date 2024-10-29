package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemAxeTemplate
import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemGoldenAxeTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ToolMaterials
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemGoldenPickaxeTemplate: ConiumItemAxeTemplate(ConiumTemplates.GOLDEN_PICKAXE, ToolMaterials.GOLD) {
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
