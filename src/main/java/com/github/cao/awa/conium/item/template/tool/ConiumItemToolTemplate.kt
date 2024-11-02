package com.github.cao.awa.conium.item.template.tool

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

open class ConiumItemToolTemplate(
    name: String,
    private val material: ToolMaterial?,
    private val effectiveBlocks: TagKey<Block> = BlockTags.AIR,
    private val attackDamage: Float = -1F,
    private val attackSpeed: Float = -1F,
    private val durability: Int = -1,
    private val isWeapon: Boolean = false,
) : ConiumItemTemplate(name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: RegistryWrapper.WrapperLookup): ConiumItemToolTemplate {
            if (element is JsonObject) {
                return ConiumItemToolTemplate(
                    ConiumTemplates.TOOL,
                    createMaterial(element["material"].asString),
                    element["effective_blocks"]?.let { tag -> createEffectiveBlocks(tag.asString) } ?: BlockTags.AIR,
                    element["attack_damage"]?.asFloat ?: -1F,
                    element["attack_speed"]?.asFloat ?: -1F,
                    element["durability"]?.asInt ?: -1,
                    element["is_weapon"]?.asBoolean ?: false,
                )
            }
            throw IllegalArgumentException("Not supported syntax: $element")
        }

        private fun createMaterial(name: String): ToolMaterial {
            return when (name) {
                "wooden", "wood" -> ToolMaterial.WOOD
                "stone" -> ToolMaterial.STONE
                "iron" -> ToolMaterial.IRON
                "golden", "gold" -> ToolMaterial.GOLD
                "diamond" -> ToolMaterial.DIAMOND
                "netherite" -> ToolMaterial.NETHERITE
                else -> throw IllegalArgumentException("Unknown tool material: $name")
            }
        }

        private fun createEffectiveBlocks(name: String): TagKey<Block> {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(name))
        }
    }

    override fun complete(item: ConiumItem) {
        // Item is tool, post hit to increments 'USED' stat data.
        item.shouldPostHit = true
    }

    override fun settings(settings: Item.Settings) {
        // When material template present, apply default settings.
        this.material?.applyToolSettings(settings, this.effectiveBlocks, this.attackDamage, this.attackSpeed)

        // Override the durability when it defined explicitly.
        if (this.durability > 0) {
            settings.maxDamage(this.durability)
        }
    }
}
