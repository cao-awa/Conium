package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.GOLDEN_PICKAXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemGoldenPickaxeTemplate : ConiumItemPickaxeTemplate(
    GOLDEN_PICKAXE,
    ToolMaterial.GOLD
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemGoldenPickaxeTemplate = element.createIfJsonObject(::ConiumItemGoldenPickaxeTemplate) {
            throw IllegalArgumentException("Not supported syntax: $it")
        }!!
    }
}
