package com.github.cao.awa.conium.kotlin.extent.recipe

import net.minecraft.world.item.crafting.RecipeType

val RecipeType<*>.coniumName: String
    get() = when (this) {
        RecipeType.SMELTING -> "smelting"
        RecipeType.BLASTING -> "blasting"
        RecipeType.SMOKING -> "smoking"
        RecipeType.CAMPFIRE_COOKING -> "campfire"
        else -> ""
    }
