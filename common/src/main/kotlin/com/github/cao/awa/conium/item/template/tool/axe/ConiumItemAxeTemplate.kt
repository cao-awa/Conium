package com.github.cao.awa.conium.item.template.tool.axe

import com.github.cao.awa.conium.item.template.durability.ConiumDurabilityTemplate
import com.github.cao.awa.conium.item.template.tool.ConiumItemToolTemplate
import com.github.cao.awa.conium.kotlin.extend.innate.changeIfIs
import com.github.cao.awa.conium.kotlin.extend.json.ifJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.TOOL
import com.google.gson.JsonElement
import net.minecraft.block.Block
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.TagKey

open class ConiumItemAxeTemplate(
    material: ToolMaterial,
    effectiveBlocks: TagKey<Block> = BlockTags.AXE_MINEABLE,
    attackDamage: Float,
    attackSpeed: Float,
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
        fun create(element: JsonElement): ConiumItemAxeTemplate = element.ifJsonObject(
            {
                createWith(
                    it,
                    TOOL,
                    ::ConiumItemAxeTemplate,
                    effectiveBlocks = { BlockTags.AXE_MINEABLE }
                )
            },
            notSupported()
        )!!
    }
}
