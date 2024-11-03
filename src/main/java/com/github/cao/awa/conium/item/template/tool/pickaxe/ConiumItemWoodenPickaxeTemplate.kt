package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.template.ConiumTemplates.Item.WOODEN_PICKAXE
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemWoodenPickaxeTemplate: ConiumItemPickaxeTemplate(
    WOODEN_PICKAXE,
    ToolMaterial.WOOD
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemWoodenPickaxeTemplate {
            if (element is JsonObject) {
                return ConiumItemWoodenPickaxeTemplate()
            }
            throw IllegalArgumentException("Not supported syntax: $element")
        }
    }
}
