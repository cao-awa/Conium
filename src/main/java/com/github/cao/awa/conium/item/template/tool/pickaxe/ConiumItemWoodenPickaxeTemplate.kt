package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemAxeTemplate
import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemWoodenAxeTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ToolMaterials
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemWoodenPickaxeTemplate: ConiumItemAxeTemplate(ConiumTemplates.WOODEN_PICKAXE, ToolMaterials.WOOD) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemWoodenAxeTemplate {
            if (element is JsonObject) {
                return ConiumItemWoodenAxeTemplate()
            }
            throw IllegalArgumentException("Not supported syntax: $element")
        }
    }
}
