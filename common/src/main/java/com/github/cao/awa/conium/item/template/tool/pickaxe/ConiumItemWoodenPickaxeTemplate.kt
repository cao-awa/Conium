package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.WOODEN_PICKAXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper

class ConiumItemWoodenPickaxeTemplate : ConiumItemPickaxeTemplate(
    ToolMaterial.WOOD,
    name = WOODEN_PICKAXE
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumItemWoodenPickaxeTemplate = element.createIfJsonObject(::ConiumItemWoodenPickaxeTemplate, notSupported())!!
    }
}
