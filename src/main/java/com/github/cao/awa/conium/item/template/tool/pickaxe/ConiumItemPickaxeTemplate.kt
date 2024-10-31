package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.item.template.tool.ConiumItemToolTemplate
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.tag.BlockTags

open class ConiumItemPickaxeTemplate(name: String, material: ToolMaterial) : ConiumItemToolTemplate(
    name,
    material,
    BlockTags.PICKAXE_MINEABLE,
    1.0F,
    -2.8F
) {
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
