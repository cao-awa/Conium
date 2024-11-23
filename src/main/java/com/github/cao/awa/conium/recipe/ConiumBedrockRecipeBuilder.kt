package com.github.cao.awa.conium.recipe

import com.github.cao.awa.conium.recipe.template.ConiumRecipeTemplate
import com.github.cao.awa.conium.template.ConiumBuilderWithTemplates
import com.github.cao.awa.conium.template.ConiumTemplate
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockRecipe.RECIPE_FURNACE
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockRecipe.RECIPE_SHAPED
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockRecipe.RECIPE_SHAPELESS
import com.google.gson.JsonObject
import net.minecraft.recipe.Recipe
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class ConiumBedrockRecipeBuilder : ConiumBuilderWithTemplates<
        ConiumBedrockRecipeBuilder,
        Nothing,
        List<Recipe<*>>,
        ConiumRecipeTemplate<Recipe<*>>>(
    ::build
) {
    companion object {
        private val bedrockRecipes = listOf(
            RECIPE_SHAPED,
            RECIPE_SHAPELESS,
            RECIPE_FURNACE,
        )

        @JvmStatic
        fun create(template: ConiumRecipeTemplate<Recipe<*>>): ConiumBedrockRecipeBuilder {
            return ConiumBedrockRecipeBuilder().apply {
                addTemplate(template)
            }
        }

        @JvmStatic
        fun findBedrock(jsonObject: JsonObject, registryLookup: WrapperLookup): List<Recipe<*>> {
            for (bedrockRecipe in this.bedrockRecipes) {
                jsonObject[bedrockRecipe]?.asJsonObject?.let {
                    return ConiumTemplate.deserializeRecipeTemplate(bedrockRecipe, it, registryLookup).fold(
                        { template ->
                            create(template).build()
                        }
                    ) { thr ->
                        throw IllegalArgumentException("Unable to deserialize bedrock recipe: $it", thr)
                    }
                }
            }

            throw IllegalArgumentException("Unable to find bedrock recipe in: $jsonObject")
        }

        fun build(template: ConiumBedrockRecipeBuilder): List<Recipe<*>> = template.templates[0].results()
    }
}
