package com.github.cao.awa.conium.recipe.template

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.template.ConiumTemplate
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.Recipe
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier

abstract class ConiumRecipeTemplate<T : Recipe<*>>(name: String) : ConiumTemplate<T, Nothing>(name) {
    companion object {
        fun createItem(jsonObject: JsonObject, name: String): ItemStack {
            return jsonObject[name]!!.let { result ->
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
        }

        fun createItemNoData(jsonObject: JsonObject, name: String): ItemStack {
            return jsonObject[name]!!.let { result ->
                val item = Registries.ITEM.get(Identifier.of(result.asString))

                ItemStack(
                    item,
                    1
                )
            }
        }

        fun createIngredient(element: JsonElement): Ingredient {
            return if (element is JsonObject) {
                Ingredient.ofItem(Registries.ITEM.get(Identifier.of(element["item"].asString)))
            } else {
                Ingredient.ofItem(Registries.ITEM.get(Identifier.of(element.asString)))
            }
        }

        fun <T: ConiumRecipeTemplate<*>> createBasic(jsonObject: JsonObject, template: T, resultName: String = "result") {
            template.identifier = Identifier.of(jsonObject["description"].asJsonObject["identifier"].asString)

            template.result = createItem(jsonObject, resultName)

            template.group = jsonObject["group"].asString
        }
    }

    lateinit var identifier: Identifier
    lateinit var group: String
    lateinit var result: ItemStack

    override fun attach(item: T) {

    }

    override fun complete(item: T) {

    }
}
