package com.github.cao.awa.conium.recipe.template.bedrock.shape

import com.github.cao.awa.conium.kotlin.extent.json.asObject
import com.github.cao.awa.conium.kotlin.extent.json.mapArray
import com.github.cao.awa.conium.recipe.template.ConiumRecipeTemplate
import com.github.cao.awa.conium.template.recipe.bedrock.BedrockRecipeComponents.RECIPE_SHAPED
import com.google.gson.JsonElement
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.ShapedRecipePattern
import net.minecraft.world.item.crafting.ShapedRecipe
import net.minecraft.world.item.crafting.CraftingRecipe
import net.minecraft.world.item.crafting.CraftingBookCategory
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.ItemStackTemplate

class BedrockRecipeShapedComponent : ConiumRecipeTemplate<ShapedRecipe>(RECIPE_SHAPED) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): BedrockRecipeShapedComponent {
            return asObject(element) {
                BedrockRecipeShapedComponent().also { component -> BedrockRecipeShapedComponent
                    createBasic(this, component)

                    component.keys = asObject(this["key"]) {
                        val ingredients: MutableMap<Char, Ingredient> = HashMap()

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
            Recipe.CommonInfo(true),
            CraftingRecipe.CraftingBookInfo(CraftingBookCategory.MISC, this.group),
            ShapedRecipePattern.of(
                this.keys,
                this.pattern
            ),
            ItemStackTemplate.fromNonEmptyStack(this.result)
        )
    }
}
