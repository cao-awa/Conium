package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.item.template.durability.ConiumDurabilityTemplate
import com.github.cao.awa.conium.item.template.tool.ConiumItemToolTemplate
import com.github.cao.awa.conium.kotlin.extent.innate.changeIfIs
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.TOOL
import com.google.gson.JsonElement
import net.minecraft.block.Block
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.TagKey

open class ConiumItemPickaxeTemplate(
    material: ToolMaterial,
    effectiveBlocks: TagKey<Block> = BlockTags.AXE_MINEABLE,
    attackDamage: Float = 1.0F,
    attackSpeed: Float = -2.8F,
    disableBlockingForSeconds: Float = 0F,
    durability: Int = -1,
    isWeapon: Boolean = false,
    damageChance: IntRange = ConiumDurabilityTemplate.defaultChance,
    name: String
) : ConiumItemToolTemplate(
    material,
    effectiveBlocks,
    attackDamage,
    attackSpeed,
    disableBlockingForSeconds,
    durability.changeIfIs(-1) { material.durability },
    isWeapon,
    damageChance,
    name
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumItemPickaxeTemplate = element.ifJsonObject(
            {
                createWith(
                    it,
                    TOOL,
                    ::ConiumItemPickaxeTemplate,
                    effectiveBlocks = { BlockTags.PICKAXE_MINEABLE }
                )
            },
            notSupported()
        )!!
    }
}
