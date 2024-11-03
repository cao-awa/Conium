package com.github.cao.awa.conium.recipe.template.bedrock.shape

import com.github.cao.awa.conium.recipe.template.ConiumRecipeTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockRecipe.RECIPE_SHAPED
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RawShapedRecipe
import net.minecraft.recipe.ShapedRecipe
import net.minecraft.recipe.book.CraftingRecipeCategory
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumBedrockRecipeShapedTemplate : ConiumRecipeTemplate<ShapedRecipe>(RECIPE_SHAPED) {
    companion object {
        @JvmStatic
        fun create(jsonObject: JsonElement, registryLookup: WrapperLookup): ConiumBedrockRecipeShapedTemplate {
            jsonObject as JsonObject

            return ConiumBedrockRecipeShapedTemplate().also {
                createBasic(jsonObject, it)
            }.also {
                it.keys = jsonObject["key"]!!.let { keys ->
                    val ingredients = CollectionFactor.hashMap<Char, Ingredient>()

                    keys as JsonObject

                    for ((key, ingredient) in keys.entrySet()) {
                        ingredients[key.toCharArray()[0]] = ingredient.let(::createIngredient)
                    }

                    ingredients
                }

                it.pattern = jsonObject["pattern"].asJsonArray.toList().map(JsonElement::getAsString)
            }
        }
    }

    lateinit var keys: Map<Char, Ingredient>
    lateinit var pattern: List<String>

    override fun result(): ShapedRecipe {
        return ShapedRecipe(
            this.group,
            CraftingRecipeCategory.MISC,
            RawShapedRecipe.create(
                this.keys,
                this.pattern
            ),
            this.result,
            true
        )
    }
}
