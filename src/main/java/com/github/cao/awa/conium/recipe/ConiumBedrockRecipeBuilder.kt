package com.github.cao.awa.conium.recipe

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RawShapedRecipe
import net.minecraft.recipe.Recipe
import net.minecraft.recipe.ShapedRecipe
import net.minecraft.recipe.ShapelessRecipe
import net.minecraft.recipe.book.CraftingRecipeCategory
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.util.Identifier
import net.minidev.json.JSONArray

class ConiumBedrockRecipeBuilder(val type: String, val identifier: Identifier) {
    companion object {
        @JvmStatic
        fun create(type: String, jsonObject: JsonObject, registryLookup: WrapperLookup): ConiumBedrockRecipeBuilder {
            val identifier = jsonObject["description"].asJsonObject["identifier"].asString

            return ConiumBedrockRecipeBuilder(type, Identifier.of(identifier)).also {
                it.result = jsonObject["result"]!!.let { result ->
                    val count: Int
                    val resultItemName: String

                    if (result is JsonObject) {
                        count = ConiumItemTemplate.validateStackSize(result["count"]?.asInt ?: 1)
                        resultItemName = result["item"].asString
                    } else {
                        count = 1
                        resultItemName = result.asString
                    }

                    val item = Registries.ITEM.get(Identifier.of(resultItemName))

                    ItemStack(
                        item,
                        count
                    )
                }

                it.group = jsonObject["group"].asString

                if (type == "minecraft:recipe_shaped") {
                    it.keys = jsonObject["key"]!!.let { keys ->
                        val ingredients = CollectionFactor.hashMap<Char, Ingredient>()

                        keys as JsonObject

                        for ((key, ingredient) in keys.entrySet()) {
                            ingredients[key.toCharArray()[0]] = ingredient.let { theIngredient ->
                                if (theIngredient is JsonObject) {
                                    Ingredient.ofItem(Registries.ITEM.get(Identifier.of(theIngredient["item"].asString)))
                                } else {
                                    Ingredient.ofItem(Registries.ITEM.get(Identifier.of(theIngredient.asString)))
                                }
                            }
                        }

                        ingredients
                    }

                    it.pattern = jsonObject["pattern"].asJsonArray.toList().map(JsonElement::getAsString)
                } else {
                    it.ingredients = jsonObject["ingredients"]!!.let { ingredients ->
                        val list = CollectionFactor.arrayList<Ingredient>()

                        ingredients as JsonArray

                        ingredients.forEach { ingredient ->
                            list.add(ingredient.let { theIngredient ->
                                if (theIngredient is JsonObject) {
                                    Ingredient.ofItem(Registries.ITEM.get(Identifier.of(theIngredient["item"].asString)))
                                } else {
                                    Ingredient.ofItem(Registries.ITEM.get(Identifier.of(theIngredient.asString)))
                                }
                            })
                        }

                        list
                    }
                }
            }
        }
    }

    lateinit var group: String
    lateinit var result: ItemStack
    lateinit var keys: Map<Char, Ingredient>
    lateinit var ingredients: List<Ingredient>
    lateinit var pattern: List<String>

    fun build(): Recipe<*>? {
            if (this.type == "minecraft:recipe_shaped") {
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
            } else if (this.type == "minecraft:recipe_shapeless") {
                return ShapelessRecipe(
                    this.group,
                    CraftingRecipeCategory.MISC,
                    this.result,
                    this.ingredients
                )
            }


        throw IllegalArgumentException("Unknown recipe type: '${this.type}'")
    }
}
