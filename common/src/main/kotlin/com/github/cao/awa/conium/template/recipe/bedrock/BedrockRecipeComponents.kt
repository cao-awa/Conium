package com.github.cao.awa.conium.template.recipe.bedrock

import com.github.cao.awa.conium.recipe.template.bedrock.furnace.BedrockRecipeFurnaceComponent
import com.github.cao.awa.conium.recipe.template.bedrock.shape.BedrockRecipeShapedComponent
import com.github.cao.awa.conium.recipe.template.bedrock.shape.BedrockRecipeShapelessComponent
import com.github.cao.awa.conium.template.ConiumTemplate

/**
 * Bedrock components register.
 *
 * Ordering with bedrock wiki:
 * https://wiki.bedrock.dev/loot/recipes
 *
 * @author cao_awa
 * @author 草二号机
 *
 * @since 1.0.0
 */
object BedrockRecipeComponents {
    const val RECIPE_SHAPELESS: String = "minecraft:recipe_shapeless"
    const val RECIPE_SHAPED: String = "minecraft:recipe_shaped"
    const val RECIPE_FURNACE: String = "minecraft:recipe_furnace"

    fun initBedrockRecipeComponents() {
        ConiumTemplate.registerRecipe(
            RECIPE_SHAPELESS,
            BedrockRecipeShapelessComponent::create,
            true
        )
        ConiumTemplate.registerRecipe(
            RECIPE_SHAPED,
            BedrockRecipeShapedComponent::create,
            true
        )
        ConiumTemplate.registerRecipe(
            RECIPE_FURNACE,
            BedrockRecipeFurnaceComponent::create,
            true
        )
    }
}