package com.github.cao.awa.conium.recipe.template.bedrock.shape

import com.github.cao.awa.conium.recipe.template.ConiumRecipeTemplate
import com.github.cao.awa.conium.template.recipe.bedrock.BedrockRecipeComponents.RECIPE_SHAPELESS
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.ShapelessRecipe
import net.minecraft.recipe.book.CraftingRecipeCategory

class BedrockRecipeShapelessComponent : ConiumRecipeTemplate<ShapelessRecipe>(RECIPE_SHAPELESS) {
    companion object {
        @JvmStatic
        fun create(jsonObject: JsonElement): BedrockRecipeShapelessComponent {
            jsonObject as JsonObject

            return BedrockRecipeShapelessComponent().also {
                createBasic(jsonObject, it)
            }.also {
                it.ingredients = jsonObject["ingredients"]!!.let { ingredients ->
                    val list = CollectionFactor.arrayList<Ingredient>()

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
            this.group,
            CraftingRecipeCategory.MISC,
            this.result,
            this.ingredients
        )
    }
}
