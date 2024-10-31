package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemDiamondPickaxeTemplate: ConiumItemPickaxeTemplate(ConiumTemplates.DIAMOND_PICKAXE, ToolMaterial.DIAMOND) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemDiamondPickaxeTemplate {
            if (element is JsonObject) {
                return ConiumItemDiamondPickaxeTemplate()
            }
            throw IllegalArgumentException("Not supported syntax: $element")
        }
    }
}
