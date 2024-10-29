package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.item.template.tool.ConiumItemToolTemplate
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.tag.BlockTags

open class ConiumItemAxeTemplate(name: String, material: ToolMaterial) : ConiumItemToolTemplate(name, material, BlockTags.AXE_MINEABLE) {
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