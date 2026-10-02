package com.github.cao.awa.conium.item.template.tool.pickaxe

import com.github.cao.awa.conium.exception.Exceptions.notSupported
import com.github.cao.awa.conium.item.template.durability.ConiumDurabilityTemplate
import com.github.cao.awa.conium.item.template.tool.ConiumItemToolTemplate
import com.github.cao.awa.conium.kotlin.extent.innate.changeIfIs
import com.github.cao.awa.conium.kotlin.extent.json.ifJsonObject
import com.github.cao.awa.conium.template.item.conium.ConiumItemTemplates.TOOL
import com.google.gson.JsonElement
import net.minecraft.world.level.block.Block
import net.minecraft.world.item.ToolMaterial
import net.minecraft.tags.BlockTags
import net.minecraft.tags.TagKey

open class ConiumItemPickaxeTemplate(
    material: ToolMaterial,
    effectiveBlocks: TagKey<Block> = BlockTags.MINEABLE_WITH_PICKAXE,
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
                    effectiveBlocks = { BlockTags.MINEABLE_WITH_PICKAXE }
                )
            },
            notSupported()
        )!!
    }
}
