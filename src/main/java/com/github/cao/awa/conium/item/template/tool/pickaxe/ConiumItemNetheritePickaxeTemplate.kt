package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.NETHERITE_PICKAXE
import com.google.gson.JsonElement
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumItemNetheritePickaxeTemplate : ConiumItemPickaxeTemplate(
    NETHERITE_PICKAXE,
    ToolMaterial.NETHERITE
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemNetheritePickaxeTemplate = element.createIfJsonObject(::ConiumItemNetheritePickaxeTemplate, notSupported())!!
    }
}
