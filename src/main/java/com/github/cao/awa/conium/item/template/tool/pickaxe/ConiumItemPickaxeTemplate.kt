package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.item.template.tool.ConiumItemToolTemplate
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item
import net.minecraft.item.ToolMaterial
import net.minecraft.item.ToolMaterials
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.registry.tag.BlockTags

open class ConiumItemPickaxeTemplate(name: String, material: ToolMaterial) : ConiumItemToolTemplate(name, material, BlockTags.PICKAXE_MINEABLE) {
    companion object {
//        @JvmStatic
//        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumItemPickaxeTemplate {
//            if (element is JsonObject) {
//                return ConiumItemPickaxeTemplate()
//            }
//            throw IllegalArgumentException("Not supported syntax: $element")
//        }
    }
}
