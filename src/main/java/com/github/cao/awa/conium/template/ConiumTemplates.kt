package com.github.cao.awa.conium.template

import com.github.cao.awa.conium.item.template.egg.ConiumSpawnEggTemplate
import com.github.cao.awa.conium.item.template.food.ConiumFoodTemplate
import com.github.cao.awa.conium.item.template.tool.axe.*
import com.github.cao.awa.conium.template.ConiumTemplate.Companion.register
import com.google.gson.JsonElement
import net.minecraft.registry.RegistryWrapper.WrapperLookup

object ConiumTemplates {
    const val SPAWN_EGG: String = "spawn_egg"

    const val FOOD: String = "food"

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

        // Pickaxes.
        register(
            WOODEN_PICKAXE,
            ConiumItemWoodenAxeTemplate::create
        )

        register(
            STONE_PICKAXE,
            ConiumItemStoneAxeTemplate::create
        )

        register(
            IRON_PICKAXE,
            ConiumItemIronAxeTemplate::create
        )

        register(
            GOLDEN_PICKAXE,
            ConiumItemGoldenAxeTemplate::create
        )

        register(
            DIAMOND_PICKAXE,
            ConiumItemDiamondAxeTemplate::create
        )
        register(
            NETHERITE_PICKAXE,
            ConiumItemNetheriteAxeTemplate::create
        )
    }
}
