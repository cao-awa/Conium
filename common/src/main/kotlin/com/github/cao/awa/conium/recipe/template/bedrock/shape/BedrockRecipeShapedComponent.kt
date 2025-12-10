package com.github.cao.awa.conium.recipe.template.bedrock.shape

import com.github.cao.awa.conium.kotlin.extent.json.asObject
import com.github.cao.awa.conium.kotlin.extent.json.mapArray
import com.github.cao.awa.conium.recipe.template.ConiumRecipeTemplate
import com.github.cao.awa.conium.template.recipe.bedrock.BedrockRecipeComponents.RECIPE_SHAPED
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonElement
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RawShapedRecipe
import net.minecraft.recipe.ShapedRecipe
import net.minecraft.recipe.book.CraftingRecipeCategory

class BedrockRecipeShapedComponent : ConiumRecipeTemplate<ShapedRecipe>(RECIPE_SHAPED) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): BedrockRecipeShapedComponent {
            return asObject(element) {
                BedrockRecipeShapedComponent().also { component -> BedrockRecipeShapedComponent
                    createBasic(this, component)

                    component.keys = asObject(this["key"]) {
                        val ingredients: MutableMap<Char, Ingredient> = CollectionFactor.hashMap()

                        for ((key: String, ingredient: JsonElement) in entrySet()) {
                            ingredients[key.toCharArray()[0]] = ingredient.let(::createIngredient)
                        }

                        ingredients
                    }

                    component.pattern = mapArray(
                        "pattern",
                        JsonElement::getAsString
                    )
                }
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
