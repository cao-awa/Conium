package com.github.cao.awa.conium.datapack.recipe

import net.minecraft.core.HolderLookup
import net.minecraft.world.item.crafting.RecipeManager
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class ConiumRecipeManager(registries: HolderLookup.Provider) : RecipeManager(registries) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumRecipeManager")
    }
}
