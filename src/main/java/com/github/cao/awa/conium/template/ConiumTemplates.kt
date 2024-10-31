package com.github.cao.awa.conium.template

//import com.github.cao.awa.conium.item.template.consumable.ConiumConsumableTemplate
import com.github.cao.awa.conium.item.template.consumable.ConiumConsumableTemplate
import com.github.cao.awa.conium.item.template.egg.ConiumSpawnEggTemplate
import com.github.cao.awa.conium.item.template.food.ConiumFoodTemplate
import com.github.cao.awa.conium.item.template.tool.axe.*
import com.github.cao.awa.conium.item.template.tool.pickaxe.*
import com.github.cao.awa.conium.template.ConiumTemplate.Companion.register

object ConiumTemplates {
    const val SPAWN_EGG: String = "spawn_egg"

    const val FOOD: String = "food"

    const val CONSUMABLE: String = "consumable"

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

    fun init() {
        // Spawn egg.
        register(
            SPAWN_EGG,
            ConiumSpawnEggTemplate::create
        )

        // Food.
        register(
            FOOD,
            ConiumFoodTemplate::create
        )

        // Consumable.
        register(
            CONSUMABLE,
            ConiumConsumableTemplate::create
        )

        // Axes.
        register(
            WOODEN_AXE,
            ConiumItemWoodenAxeTemplate::create
        )

        register(
            STONE_AXE,
            ConiumItemStoneAxeTemplate::create
        )

        register(
            IRON_AXE,
            ConiumItemIronAxeTemplate::create
        )

        register(
            GOLDEN_AXE,
            ConiumItemGoldenAxeTemplate::create
        )

        register(
            DIAMOND_AXE,
            ConiumItemDiamondAxeTemplate::create
        )
        register(
            NETHERITE_AXE,
            ConiumItemNetheriteAxeTemplate::create
        )

        // Pickaxes.
        register(
            WOODEN_PICKAXE,
            ConiumItemWoodenPickaxeTemplate::create
        )

        register(
            STONE_PICKAXE,
            ConiumItemStonePickaxeTemplate::create
        )

        register(
            IRON_PICKAXE,
            ConiumItemIronPickaxeTemplate::create
        )

        register(
            GOLDEN_PICKAXE,
            ConiumItemGoldenPickaxeTemplate::create
        )

        register(
            DIAMOND_PICKAXE,
            ConiumItemDiamondPickaxeTemplate::create
        )
        register(
            NETHERITE_PICKAXE,
            ConiumItemNetheritePickaxeTemplate::create
        )
    }
}
