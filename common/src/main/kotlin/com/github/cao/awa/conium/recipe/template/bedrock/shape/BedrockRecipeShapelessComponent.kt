package com.github.cao.awa.conium.recipe.template.bedrock.shape

import com.github.cao.awa.conium.recipe.template.ConiumRecipeTemplate
import com.github.cao.awa.conium.template.recipe.bedrock.BedrockRecipeComponents.RECIPE_SHAPELESS
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.ShapelessRecipe
import net.minecraft.world.item.crafting.CraftingRecipe
import net.minecraft.world.item.crafting.CraftingBookCategory
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.ItemStackTemplate

class BedrockRecipeShapelessComponent : ConiumRecipeTemplate<ShapelessRecipe>(RECIPE_SHAPELESS) {
    companion object {
        @JvmStatic
        fun create(jsonObject: JsonElement): BedrockRecipeShapelessComponent {
            jsonObject as JsonObject

            return BedrockRecipeShapelessComponent().also {
                createBasic(jsonObject, it)
            }.also {
                it.ingredients = jsonObject["ingredients"]!!.let { ingredients ->
                    val list = ArrayList<Ingredient>()

                    ingredients as JsonArray

                    ingredients.forEach { ingredient ->
                        list.add(ingredient.let(::createIngredient))
                    }

                    list
                }
            }
        }
    }

    lateinit var ingredients: List<Ingredient>

    override fun result(): ShapelessRecipe {
        return ShapelessRecipe(
            Recipe.CommonInfo(true),
            CraftingRecipe.CraftingBookInfo(CraftingBookCategory.MISC, this.group),
            ItemStackTemplate.fromNonEmptyStack(this.result),
            this.ingredients
        )
    }
}
