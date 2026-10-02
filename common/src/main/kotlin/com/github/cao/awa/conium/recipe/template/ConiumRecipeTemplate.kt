package com.github.cao.awa.conium.recipe.template

import com.github.cao.awa.conium.template.ConiumTemplate
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier

abstract class ConiumRecipeTemplate<T : Recipe<*>>(name: String) : ConiumTemplate<T, Nothing>(name = name) {
    companion object {
        fun createItemNoData(jsonObject: JsonObject, name: String): ItemStack {
            return ItemStack(
                BuiltInRegistries.ITEM.getValue(Identifier.parse(jsonObject[name]!!.asString)), 1)
        }

        fun createIngredient(element: JsonElement): Ingredient {
            return if (element is JsonObject) {
                Ingredient.of(BuiltInRegistries.ITEM.getValue(Identifier.parse(element["item"].asString)))
            } else {
                Ingredient.of(BuiltInRegistries.ITEM.getValue(Identifier.parse(element.asString)))
            }
        }

        fun <T : ConiumRecipeTemplate<*>> createBasic(jsonObject: JsonObject, template: T, resultName: String = "result") {
            template.identifier = Identifier.parse(jsonObject["description"].asJsonObject["identifier"].asString)

            template.result = createItemStack(jsonObject, resultName)

            template.group = jsonObject["group"].asString
        }
    }

    lateinit var identifier: Identifier
    lateinit var group: String
    lateinit var result: ItemStack

    override fun attach(target: T) {

    }

    override fun complete(target: T) {

    }
}
