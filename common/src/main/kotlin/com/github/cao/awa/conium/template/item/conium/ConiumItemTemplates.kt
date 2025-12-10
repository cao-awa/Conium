package com.github.cao.awa.conium.template.item.conium

import com.github.cao.awa.conium.item.template.action.ConiumUseActionTemplate
import com.github.cao.awa.conium.item.template.armor.ConiumArmorTemplate
import com.github.cao.awa.conium.item.template.consumable.ConiumConsumableTemplate
import com.github.cao.awa.conium.item.template.consume.ConiumConsumeOnUsedTemplate
import com.github.cao.awa.conium.item.template.convert.block.ConiumUsedOnBlockConvertToTemplate
import com.github.cao.awa.conium.item.template.destory.ConiumCanDestroyInCreativeTemplate
import com.github.cao.awa.conium.item.template.egg.ConiumSpawnEggTemplate
import com.github.cao.awa.conium.item.template.entity.placer.ConiumEntityPlacerTemplate
import com.github.cao.awa.conium.item.template.food.ConiumFoodTemplate
import com.github.cao.awa.conium.item.template.fuel.ConiumFuelTemplate
import com.github.cao.awa.conium.item.template.glint.ConiumGlintTemplate
import com.github.cao.awa.conium.item.template.ignite.ConiumClearEntityIgniteTemplate
import com.github.cao.awa.conium.item.template.ignite.ConiumIgniteEntityTemplate
import com.github.cao.awa.conium.item.template.rarity.ConiumRarityTemplate
import com.github.cao.awa.conium.item.template.rarity.epic.ConiumCommonRarityTemplate
import com.github.cao.awa.conium.item.template.rarity.epic.ConiumEpicRarityTemplate
import com.github.cao.awa.conium.item.template.rarity.epic.ConiumRareRarityTemplate
import com.github.cao.awa.conium.item.template.rarity.epic.ConiumUncommonRarityTemplate
import com.github.cao.awa.conium.item.template.stack.count.ConiumStackMaxCountTemplate
import com.github.cao.awa.conium.item.template.tool.ConiumItemToolTemplate
import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemDiamondAxeTemplate
import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemGoldenAxeTemplate
import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemIronAxeTemplate
import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemNetheriteAxeTemplate
import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemStoneAxeTemplate
import com.github.cao.awa.conium.item.template.tool.axe.ConiumItemWoodenAxeTemplate
import com.github.cao.awa.conium.item.template.tool.mining.ConiumForceMiningSpeedTemplate
import com.github.cao.awa.conium.item.template.tool.pickaxe.ConiumItemDiamondPickaxeTemplate
import com.github.cao.awa.conium.item.template.tool.pickaxe.ConiumItemGoldenPickaxeTemplate
import com.github.cao.awa.conium.item.template.tool.pickaxe.ConiumItemIronPickaxeTemplate
import com.github.cao.awa.conium.item.template.tool.pickaxe.ConiumItemNetheritePickaxeTemplate
import com.github.cao.awa.conium.item.template.tool.pickaxe.ConiumItemStonePickaxeTemplate
import com.github.cao.awa.conium.item.template.tool.pickaxe.ConiumItemWoodenPickaxeTemplate
import com.github.cao.awa.conium.template.ConiumTemplate

object ConiumItemTemplates {
    const val SPAWN_EGG: String = "spawn_egg"

    const val FOOD: String = "food"

    // Consumable.
    const val CONSUMABLE: String = "consumable"
    const val CONSUME_ON_USED: String = "consume_on_used"

    // Use action.
    const val USE_ACTION: String = "use_action"

    // Stack max count.
    const val STACK_MAX_COUNT: String = "max_count"

    // Rarity.
    const val RARITY: String = "rarity"
    const val EPIC_RARITY: String = "epic_rarity"
    const val RARE_RARITY: String = "rare_rarity"
    const val UNCOMMON_RARITY: String = "uncommon_rarity"
    const val COMMON_RARITY: String = "common_rarity"

    // Fuel.
    const val FUEL: String = "fuel"

    // Glint.
    const val GLINT: String = "glint"

    // Armor.
    const val ARMOR: String = "armor"

    // Tool.
    const val TOOL: String = "tool"
    const val CAN_DESTROY_IN_CREATIVE: String = "can_destroy_in_creative"

    // Axes.
    const val WOODEN_AXE: String = "wooden_axe"
    const val STONE_AXE: String = "stone_axe"
    const val IRON_AXE: String = "iron_axe"
    const val GOLDEN_AXE: String = "golden_axe"
    const val DIAMOND_AXE: String = "diamond_axe"
    const val NETHERITE_AXE: String = "netherite_axe"

    // Pickaxes.
    const val WOODEN_PICKAXE: String = "wooden_pickaxe"
    const val STONE_PICKAXE: String = "stone_pickaxe"
    const val IRON_PICKAXE: String = "iron_pickaxe"
    const val GOLDEN_PICKAXE: String = "golden_pickaxe"
    const val DIAMOND_PICKAXE: String = "diamond_pickaxe"
    const val NETHERITE_PICKAXE: String = "netherite_pickaxe"

    // Mining speed
    const val FORCE_MINING_SPEED: String = "force_mining_speed"

    // Ignite entity.
    const val IGNITE: String = "ignite"
    const val CLEAR_IGNITE: String = "clear_ignite"

    // Entity placer.
    const val ENTITY_PLACER: String = "entity_placer"


    // Convert.
    const val USED_ON_BLOCK_CONVERT_TO: String = "used_on_block_convert_to"

    fun initItemTemplates() {
        // Spawn egg.
        ConiumTemplate.registerItem(
            SPAWN_EGG,
            ConiumSpawnEggTemplate::create
        )

        // Food.
        ConiumTemplate.registerItem(
            FOOD,
            ConiumFoodTemplate::create
        )

        // Stack max count
        ConiumTemplate.registerItem(
            STACK_MAX_COUNT,
            ConiumStackMaxCountTemplate::create
        )

        // Consumable.
        ConiumTemplate.registerItem(
            CONSUMABLE,
            ConiumConsumableTemplate::create
        )

        ConiumTemplate.registerItem(
            CONSUME_ON_USED,
            ConiumConsumeOnUsedTemplate::create
        )

        // Use action.
        ConiumTemplate.registerItem(
            USE_ACTION,
            ConiumUseActionTemplate::create
        )

        // Tool.
        ConiumTemplate.registerItem(
            TOOL,
            ConiumItemToolTemplate::create
        )
        // Can destroy in creative.
        ConiumTemplate.registerItem(
            CAN_DESTROY_IN_CREATIVE,
            ConiumCanDestroyInCreativeTemplate::create
        )

        // Axes.
        ConiumTemplate.registerItem(
            WOODEN_AXE,
            ConiumItemWoodenAxeTemplate::create
        )
        ConiumTemplate.registerItem(
            STONE_AXE,
            ConiumItemStoneAxeTemplate::create
        )
        ConiumTemplate.registerItem(
            IRON_AXE,
            ConiumItemIronAxeTemplate::create
        )
        ConiumTemplate.registerItem(
            GOLDEN_AXE,
            ConiumItemGoldenAxeTemplate::create
        )
        ConiumTemplate.registerItem(
            DIAMOND_AXE,
            ConiumItemDiamondAxeTemplate::create
        )
        ConiumTemplate.registerItem(
            NETHERITE_AXE,
            ConiumItemNetheriteAxeTemplate::create
        )

        // Pickaxes.
        ConiumTemplate.registerItem(
            WOODEN_PICKAXE,
            ConiumItemWoodenPickaxeTemplate::create
        )
        ConiumTemplate.registerItem(
            STONE_PICKAXE,
            ConiumItemStonePickaxeTemplate::create
        )
        ConiumTemplate.registerItem(
            IRON_PICKAXE,
            ConiumItemIronPickaxeTemplate::create
        )
        ConiumTemplate.registerItem(
            GOLDEN_PICKAXE,
            ConiumItemGoldenPickaxeTemplate::create
        )
        ConiumTemplate.registerItem(
            DIAMOND_PICKAXE,
            ConiumItemDiamondPickaxeTemplate::create
        )
        ConiumTemplate.registerItem(
            NETHERITE_PICKAXE,
            ConiumItemNetheritePickaxeTemplate::create
        )

        // Mining speed.
        ConiumTemplate.registerItem(
            FORCE_MINING_SPEED,
            ConiumForceMiningSpeedTemplate::create
        )

        // Rarity.
        ConiumTemplate.registerItem(
            RARITY,
            ConiumRarityTemplate::create
        )
        ConiumTemplate.registerItem(
            EPIC_RARITY,
            ConiumEpicRarityTemplate::create
        )
        ConiumTemplate.registerItem(
            RARE_RARITY,
            ConiumRareRarityTemplate::create
        )
        ConiumTemplate.registerItem(
            UNCOMMON_RARITY,
            ConiumUncommonRarityTemplate::create
        )
        ConiumTemplate.registerItem(
            COMMON_RARITY,
            ConiumCommonRarityTemplate::create
        )

        // Fuel.
        ConiumTemplate.registerItem(
            FUEL,
            ConiumFuelTemplate::create
        )

        // Glint.
        ConiumTemplate.registerItem(
            GLINT,
            ConiumGlintTemplate::create
        )

        // Armor.
        ConiumTemplate.registerItem(
            ARMOR,
            ConiumArmorTemplate::create
        )

        // Ignite entity
        ConiumTemplate.registerItem(
            IGNITE,
            ConiumIgniteEntityTemplate::create
        )
        ConiumTemplate.registerItem(
            CLEAR_IGNITE,
            ConiumClearEntityIgniteTemplate::create
        )

        // Convert.
        ConiumTemplate.registerItem(
            USED_ON_BLOCK_CONVERT_TO,
            ConiumUsedOnBlockConvertToTemplate::create
        )

        // Entity placer.
        ConiumTemplate.registerItem(
            ENTITY_PLACER,
            ConiumEntityPlacerTemplate::create
        )
    }
}