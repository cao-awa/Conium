package com.github.cao.awa.conium.recipe.template.bedrock.furnace

import com.github.cao.awa.conium.recipe.template.ConiumRecipeTemplate
import com.github.cao.awa.conium.template.ConiumTemplates
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.recipe.*
import net.minecraft.recipe.book.CookingRecipeCategory
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumBedrockRecipeFurnaceTemplate : ConiumRecipeTemplate<Recipe<*>>(ConiumTemplates.BEDROCK_RECIPE_FURNACE) {
    companion object {
        private val furnaceTypes = mapOf(
            Pair("furnace", ::SmeltingRecipe),
            Pair("blast_furnace", ::BlastingRecipe),
            Pair("smoker", ::SmokingRecipe),
            Pair("campfire", ::CampfireCookingRecipe),
            Pair("soul_campfire", ::CampfireCookingRecipe)
        )

        @JvmStatic
        fun create(jsonObject: JsonElement, registryLookup: WrapperLookup): ConiumBedrockRecipeFurnaceTemplate {
            jsonObject as JsonObject

            return ConiumBedrockRecipeFurnaceTemplate().also {
                createBasic(jsonObject, it, "output")
            }.also {
                it.input = createIngredient(jsonObject["input"])

                it.tags = jsonObject["tags"].asJsonArray.toList().map(JsonElement::getAsString)
            }
        }
    }

    lateinit var tags: List<String>
    lateinit var input: Ingredient

    override fun results(): List<Recipe<*>> {
        return CollectionFactor.arrayList<Recipe<*>>().also {
            for (tag in this.tags) {
                furnaceTypes[tag]?.let { recipe ->
                    it.add(
                        recipe(
                            this.group,
                            CookingRecipeCategory.MISC,
                            this.input,
                            this.result,
                            0.0F,
                            200
                        )
                    )
                }
            }
        }
    }
}
