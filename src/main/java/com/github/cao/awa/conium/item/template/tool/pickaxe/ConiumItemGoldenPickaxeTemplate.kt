package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.template.ConiumTemplates.Item.GOLDEN_PICKAXE
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemGoldenPickaxeTemplate: ConiumItemPickaxeTemplate(
    GOLDEN_PICKAXE,
    ToolMaterial.GOLD
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemGoldenPickaxeTemplate {
            if (element is JsonObject) {
                return ConiumItemGoldenPickaxeTemplate()
            }
            throw IllegalArgumentException("Not supported syntax: $element")
        }
    }
}
