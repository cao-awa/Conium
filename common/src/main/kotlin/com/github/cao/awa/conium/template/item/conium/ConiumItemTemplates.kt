package com.github.cao.awa.conium.template.item.conium

import com.github.cao.awa.conium.item.template.action.ConiumUseActionTemplate
import com.github.cao.awa.conium.item.template.armor.ConiumArmorTemplate
import com.github.cao.awa.conium.item.template.compostable.ConiumCompostableTemplate
import com.github.cao.awa.conium.item.template.consumable.ConiumConsumableTemplate
import com.github.cao.awa.conium.item.template.consume.ConiumConsumeOnUsedTemplate
import com.github.cao.awa.conium.item.template.convert.block.ConiumUsedOnBlockConvertToTemplate
import com.github.cao.awa.conium.item.template.destory.ConiumCanDestroyInCreativeTemplate
import com.github.cao.awa.conium.item.template.display.name.ConiumDisplayNameTemplate
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

/**
 * Conium item templates register.
 *
 * Ordering with alphabet order or group.
 *
 * @author cao_awa
 * @author 草二号机
 *
 * @since 1.0.0
 */
object ConiumItemTemplates {
    // Armor (.A) .
    const val ARMOR: String = "armor"

    // (.A) .
    // Axes.
    const val WOODEN_AXE: String = "wooden_axe"
    const val STONE_AXE: String = "stone_axe"
    const val IRON_AXE: String = "iron_axe"
    const val GOLDEN_AXE: String = "golden_axe"
    const val DIAMOND_AXE: String = "diamond_axe"
    const val NETHERITE_AXE: String = "netherite_axe"

    // (.C) .
    const val CAN_DESTROY_IN_CREATIVE: String = "can_destroy_in_creative"
    const val CLEAR_IGNITE: String = "clear_ignite"
    const val CONSUMABLE: String = "consumable"
    const val CONSUME_ON_USED: String = "consume_on_used"
    const val COMPOSTABLE: String = "compostable"

    // (.D) .
    const val DISPLAY_NAME: String = "display_name"

    // (.E) .
    const val ENTITY_PLACER: String = "entity_placer"

    // (.F) .
    const val FOOD: String = "food"
    const val FORCE_MINING_SPEED: String = "force_mining_speed"
    const val FUEL: String = "fuel"

    // (.G) .
    const val GLINT: String = "glint"

    // (.I) .
    const val IGNITE: String = "ignite"

    // (.P) .
    // Pickaxes.
    const val WOODEN_PICKAXE: String = "wooden_pickaxe"
    const val STONE_PICKAXE: String = "stone_pickaxe"
    const val IRON_PICKAXE: String = "iron_pickaxe"
    const val GOLDEN_PICKAXE: String = "golden_pickaxe"
    const val DIAMOND_PICKAXE: String = "diamond_pickaxe"
    const val NETHERITE_PICKAXE: String = "netherite_pickaxe"

    // (.R) .
    // Rarity.
    const val RARITY: String = "rarity"
    const val EPIC_RARITY: String = "epic_rarity"
    const val RARE_RARITY: String = "rare_rarity"
    const val UNCOMMON_RARITY: String = "uncommon_rarity"
    const val COMMON_RARITY: String = "common_rarity"

    // (.S) .
    const val SPAWN_EGG: String = "spawn_egg"
    const val MAX_COUNT: String = "max_count"

    // (.T) .
    const val TOOL: String = "tool"

    // (.U) .
    const val USE_ACTION: String = "use_action"
    const val USED_ON_BLOCK_CONVERT_TO: String = "used_on_block_convert_to"

    fun initItemTemplates() {
        // Armor (.A) .
        ConiumTemplate.registerItem(
            ARMOR,
            ConiumArmorTemplate::create
        )

        // Axes (.A) .
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

        // (.C) .
        ConiumTemplate.registerItem(
            CAN_DESTROY_IN_CREATIVE,
            ConiumCanDestroyInCreativeTemplate::create
        )

        ConiumTemplate.registerItem(
            CLEAR_IGNITE,
            ConiumClearEntityIgniteTemplate::create
        )

        ConiumTemplate.registerItem(
            CONSUMABLE,
            ConiumConsumableTemplate::create
        )

        ConiumTemplate.registerItem(
            CONSUME_ON_USED,
            ConiumConsumeOnUsedTemplate::create
        )

        ConiumTemplate.registerItem(
            COMPOSTABLE,
            ConiumCompostableTemplate::create
        )

        // (.D) .
        ConiumTemplate.registerItem(
            DISPLAY_NAME,
            ConiumDisplayNameTemplate::create
        )

        // (.E) .
        ConiumTemplate.registerItem(
            ENTITY_PLACER,
            ConiumEntityPlacerTemplate::create
        )

        // (.F) .
        ConiumTemplate.registerItem(
            FOOD,
            ConiumFoodTemplate::create
        )
        ConiumTemplate.registerItem(
            FORCE_MINING_SPEED,
            ConiumForceMiningSpeedTemplate::create
        )
        ConiumTemplate.registerItem(
            FUEL,
            ConiumFuelTemplate::create
        )

        // (.G) .
        ConiumTemplate.registerItem(
            GLINT,
            ConiumGlintTemplate::create
        )

        // (.I) .
        ConiumTemplate.registerItem(
            IGNITE,
            ConiumIgniteEntityTemplate::create
        )

        // (.P) .
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

        // (.R) .
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

        // (.S) .
        ConiumTemplate.registerItem(
            SPAWN_EGG,
            ConiumSpawnEggTemplate::create
        )
        ConiumTemplate.registerItem(
            MAX_COUNT,
            ConiumStackMaxCountTemplate::create
        )

        // (.T) .
        ConiumTemplate.registerItem(
            TOOL,
            ConiumItemToolTemplate::create
        )

        // (.U) .
        ConiumTemplate.registerItem(
            USE_ACTION,
            ConiumUseActionTemplate::create
        )

        ConiumTemplate.registerItem(
            USED_ON_BLOCK_CONVERT_TO,
            ConiumUsedOnBlockConvertToTemplate::create
        )

    }
}