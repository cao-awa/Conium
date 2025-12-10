package com.github.cao.awa.conium.recipe.template.bedrock.furnace

import com.github.cao.awa.conium.kotlin.extent.json.asObject
import com.github.cao.awa.conium.kotlin.extent.json.mapArray
import com.github.cao.awa.conium.recipe.template.ConiumRecipeTemplate
import com.github.cao.awa.conium.template.recipe.bedrock.BedrockRecipeComponents.RECIPE_FURNACE
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonElement
import net.minecraft.item.ItemStack
import net.minecraft.recipe.*
import net.minecraft.recipe.book.CookingRecipeCategory

class BedrockRecipeFurnaceComponent : ConiumRecipeTemplate<Recipe<*>>(RECIPE_FURNACE) {
    companion object {
        private val furnaceTypes: Map<String, (String, CookingRecipeCategory, Ingredient, ItemStack, Float, Int) -> AbstractCookingRecipe> = mapOf(
            Pair("furnace", ::SmeltingRecipe),
            Pair("blast_furnace", ::BlastingRecipe),
            Pair("smoker", ::SmokingRecipe),
            Pair("campfire", ::CampfireCookingRecipe),
            Pair("soul_campfire", ::CampfireCookingRecipe)
        )

        @JvmStatic
        fun create(element: JsonElement): BedrockRecipeFurnaceComponent {
            return asObject(element) {
                BedrockRecipeFurnaceComponent().also { component -> BedrockRecipeFurnaceComponent
                    createBasic(this, component, "output")

                    component.input = createIngredient(this["input"])

                    component.tags = mapArray("tags", JsonElement::getAsString)
                }
            }
        }
    }

    lateinit var tags: List<String>
    lateinit var input: Ingredient

    override fun results(): List<Recipe<*>> {
        return CollectionFactor.arrayList<Recipe<*>>().also {
            for (tag: String in this.tags) {
                furnaceTypes[tag]?.let { recipe: (String, CookingRecipeCategory, Ingredient, ItemStack, Float, Int) -> AbstractCookingRecipe ->
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
