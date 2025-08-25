package com.github.cao.awa.conium.item.template.tool

import com.github.cao.awa.conium.item.setting.ConiumItemSettings
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.item.template.durability.ConiumDurabilityTemplate
import com.github.cao.awa.conium.kotlin.extend.json.ifJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.Item.TOOL
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

open class ConiumItemToolTemplate(
    private val material: ToolMaterial?,
    private val effectiveBlocks: TagKey<Block> = BlockTags.AIR,
    private val attackDamage: Float = -1F,
    private val attackSpeed: Float = -1F,
    private val disableBlockingForSeconds: Float = 0F,
    private val durability: Int = -1,
    private val isWeapon: Boolean = false,
    private val damageChance: IntRange = ConiumDurabilityTemplate.defaultChance,
    name: String
) : ConiumItemTemplate(name = name) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumItemToolTemplate = element.ifJsonObject(
            {
                createWith(it, TOOL, ::ConiumItemToolTemplate)
            },
            notSupported()
        )!!

        fun <T : ConiumItemToolTemplate> createWith(
            it: JsonObject,
            name: String,
            creator: (ToolMaterial, TagKey<Block>, Float, Float, Float, Int, Boolean, IntRange, String) -> T,
            material: (JsonObject) -> ToolMaterial = { createMaterial(it["material"].asString) },
            effectiveBlocks: (JsonObject) -> TagKey<Block> = { it["effective_blocks"]?.asString?.let(::createEffectiveBlocks) ?: BlockTags.AIR },
            attackDamage: (JsonObject) -> Float = { it["attack_damage"]?.asFloat ?: -1F },
            attackSpeed: (JsonObject) -> Float = { it["attack_speed"]?.asFloat ?: -1F },
            disableBlockingForSeconds: (JsonObject) -> Float = { it["disable_blocking_for_seconds"]?.asFloat ?: 0F },
            durability: (JsonObject) -> Int = { it["durability"]?.asInt ?: -1 },
            isWeapon: (JsonObject) -> Boolean = { it["is_weapon"]?.asBoolean ?: false },
            damageChance: (JsonObject) -> IntRange = { ConiumDurabilityTemplate.createChance(it)}
        ): T {
            return creator(
                material(it),
                effectiveBlocks(it),
                attackDamage(it),
                attackSpeed(it),
                disableBlockingForSeconds(it),
                durability(it),
                isWeapon(it),
                damageChance(it),
                name
            )
        }

        fun createMaterial(name: String): ToolMaterial {
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

        fun createEffectiveBlocks(name: String): TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, Identifier.of(name))
    }

    override fun settings(settings: ConiumItemSettings) {
        // Item is tool, post hit to increments 'USED' stat data.
        settings.shouldPostHit = true

        // Set durability damage chance.
        settings.durabilityDamageChance = this.damageChance

        // Set item is a weapon.
        settings.isWeapon = this.isWeapon
    }

    override fun settings(settings: Item.Settings) {
        // When material template present, apply default settings.
        this.material?.applyToolSettings(settings, this.effectiveBlocks, this.attackDamage, this.attackSpeed, this.disableBlockingForSeconds)

        // Override the durability when it defined explicitly.
        if (this.durability > 0) {
            settings.maxDamage(this.durability)
        }
    }
}
