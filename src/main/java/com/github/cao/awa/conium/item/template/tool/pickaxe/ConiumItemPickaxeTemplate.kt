package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.item.template.durability.ConiumDurabilityTemplate
import com.github.cao.awa.conium.item.template.tool.ConiumItemToolTemplate
import com.github.cao.awa.conium.kotlin.extent.innate.changeIfIs
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.TOOL
import com.google.gson.JsonElement
import net.minecraft.block.Block
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.TagKey

open class ConiumItemPickaxeTemplate(
    material: ToolMaterial,
    attackDamage: Float = 1.0F,
    attackSpeed: Float = -2.8F,
    isWeapon: Boolean = false,
    damageChance: IntRange = ConiumDurabilityTemplate.defaultChance,
    effectiveBlocks: TagKey<Block> = BlockTags.PICKAXE_MINEABLE,
    durability: Int = -1,
    name: String
) : ConiumItemToolTemplate(
    material,
    effectiveBlocks,
    attackDamage,
    attackSpeed,
    durability.changeIfIs(-1) { material.durability },
    isWeapon,
    damageChance,
    name
) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumItemPickaxeTemplate = element.ifJsonObject(
            {
                createWith(
                    it,
                    TOOL,
                    { material: ToolMaterial,
                      effectiveBlocks: TagKey<Block>,
                      attackDamage: Float,
                      attackSpeed: Float, durability: Int,
                      isWeapon: Boolean,
                      damageChance: IntRange,
                      name: String ->
                        ConiumItemPickaxeTemplate(material, attackDamage, attackSpeed, isWeapon, damageChance, effectiveBlocks, durability, name)
                    },
                    effectiveBlocks = { BlockTags.PICKAXE_MINEABLE }
                )
            },
            notSupported()
        )!!
    }
}
