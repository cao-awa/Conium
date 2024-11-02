package com.github.cao.awa.conium.kotlin.extent.recipe

import net.minecraft.recipe.RecipeType

val RecipeType<*>.coniumName
    get() = when (this) {
        RecipeType.SMELTING -> "smelting"
        RecipeType.BLASTING -> "blasting"
        RecipeType.SMOKING -> "smoking"
        RecipeType.CAMPFIRE_COOKING -> "campfire"
        else -> ""
    }
