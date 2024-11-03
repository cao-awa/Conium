package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.template.ConiumTemplates.Item.STONE_PICKAXE
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemStonePickaxeTemplate: ConiumItemPickaxeTemplate(
    STONE_PICKAXE,
    ToolMaterial.STONE
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemStonePickaxeTemplate {
            if (element is JsonObject) {
                return ConiumItemStonePickaxeTemplate()
            }
            throw IllegalArgumentException("Not supported syntax: $element")
        }
    }
}
