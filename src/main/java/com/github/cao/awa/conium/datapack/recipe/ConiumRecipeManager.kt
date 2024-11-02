package com.github.cao.awa.conium.datapack.recipe

import com.github.cao.awa.conium.kotlin.extent.recipe.coniumName
import com.github.cao.awa.conium.mixin.recipe.ServerRecipeManagerAccessor
import com.github.cao.awa.conium.mixin.recipe.property.RecipePropertySetAccessor
import com.github.cao.awa.conium.recipe.ConiumBedrockRecipeBuilder
import com.github.cao.awa.sinuatum.util.collection.CollectionFactor
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.mojang.serialization.JsonOps
import net.minecraft.recipe.*
import net.minecraft.recipe.ServerRecipeManager.SoleIngredientGetter
import net.minecraft.recipe.display.CuttingRecipeDisplay
import net.minecraft.recipe.input.RecipeInput
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.resource.ResourceFinder
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.featuretoggle.FeatureSet
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import net.minecraft.world.World
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors
import kotlin.jvm.optionals.getOrNull

class ConiumRecipeManager(private val registries: WrapperLookup) : ServerRecipeManager(registries) {
    companion object {
        private val LOGGER: Logger = LogManager.getLogger("ConiumRecipeManager")

        private val SOLE_INGREDIENT_GETTERS: Map<RegistryKey<RecipePropertySet>, SoleIngredientGetter> = mutableMapOf(
            Pair(
                RecipePropertySet.SMITHING_ADDITION,
                SoleIngredientGetter { recipe: Recipe<*> -> if (recipe is SmithingRecipe) recipe.addition() else Optional.empty() }
            ),
            Pair(RecipePropertySet.SMITHING_BASE,
                SoleIngredientGetter { recipe: Recipe<*> -> if (recipe is SmithingRecipe) recipe.base() else Optional.empty() }),
            Pair(RecipePropertySet.SMITHING_TEMPLATE,
                SoleIngredientGetter { recipe: Recipe<*> -> if (recipe is SmithingRecipe) recipe.template() else Optional.empty() }),
            Pair(
                RecipePropertySet.FURNACE_INPUT,
                cookingIngredientGetter(RecipeType.SMELTING)
            ),
            Pair(
                RecipePropertySet.BLAST_FURNACE_INPUT,
                cookingIngredientGetter(RecipeType.BLASTING)
            ),
            Pair(
                RecipePropertySet.SMOKER_INPUT,
                cookingIngredientGetter(RecipeType.SMOKING)
            ),
            Pair(
                RecipePropertySet.CAMPFIRE_INPUT,
                cookingIngredientGetter(RecipeType.CAMPFIRE_COOKING)
            )
        )

        private fun <X : SingleStackRecipe> cookingIngredientGetter(expectedType: RecipeType<X>): SoleIngredientGetter {
            return SoleIngredientGetter {
                if (it.type == expectedType && it is SingleStackRecipe) {
                    Optional.of(it.ingredient())
                } else {
                    Optional.empty()
                }
            }
        }

        fun filterIngredients(features: FeatureSet, ingredients: MutableList<Ingredient>): List<Ingredient> {
            ingredients.removeIf { ingredient -> !isEnabled(features, ingredient) }
            return ingredients
        }

        private fun isEnabled(features: FeatureSet, ingredient: Ingredient): Boolean {
            return ingredient.matchingItems.stream().allMatch { it.value().isEnabled(features) }
        }

        open class PropertySetBuilder(val propertySetKey: RegistryKey<RecipePropertySet>, private val ingredientGetter: SoleIngredientGetter) : Consumer<Recipe<*>> {
            private val ingredients: MutableList<Ingredient> = ArrayList()

            override fun accept(recipe: Recipe<*>) {
                ingredientGetter.apply(recipe).ifPresent(ingredients::add)
            }

            fun build(enabledFeatures: FeatureSet): RecipePropertySet {
                return RecipePropertySetAccessor.of(filterIngredients(enabledFeatures, this.ingredients))
            }
        }
    }

    private var preparedConiumRecipes: PreparedRecipes = PreparedRecipes.EMPTY
    private var coniumPropertySets: Map<RegistryKey<RecipePropertySet>, RecipePropertySet> = CollectionFactor.hashMap()
    private var coniumStonecutterRecipes: CuttingRecipeDisplay.Grouping<StonecuttingRecipe> = CuttingRecipeDisplay.Grouping.empty()
    private var coniumRecipes = listOf<ServerRecipe>()
    private var coniumRecipesByKey: Map<RegistryKey<Recipe<*>>, List<ServerRecipe>> = CollectionFactor.hashMap()

    override fun prepare(resourceManager: ResourceManager, profiler: Profiler): PreparedRecipes {
        val sortedMap: SortedMap<Identifier, Recipe<*>> = TreeMap()
        load(resourceManager, RegistryKeys.getPath(RegistryKeys.RECIPE), sortedMap)
        val list: MutableList<RecipeEntry<*>> = ArrayList(sortedMap.size)
        sortedMap.forEach { (id, recipe) ->
            val registryKey = RegistryKey.of(RegistryKeys.RECIPE, id)
            val recipeEntry: RecipeEntry<*> = RecipeEntry(registryKey, recipe)
            list.add(recipeEntry)
        }
        return PreparedRecipes.of(list)
    }

    private fun load(manager: ResourceManager, dataType: String, result: MutableMap<Identifier, Recipe<*>>) {
        val resourceFinder = ResourceFinder.json(dataType)

        for ((key, value) in resourceFinder.findResources(manager)) {
            val identifier2 = resourceFinder.toResourceId(key)

            try {
                val reader = value.reader!!

                try {
                    val ops = this.registries.getOps(JsonOps.INSTANCE)
                    val element = JsonParser.parseReader(reader)

                    // Parse recipe by vanilla recipe schema.
                    Recipe.CODEC.parse(ops, element).ifSuccess {
                        check(result.putIfAbsent(identifier2, it) == null) {
                            "Duplicate data file ignored with ID $identifier2"
                        }
                    }.ifError {
                        // If recipe are not vanilla, then it may be a bedrock recipe.
                        val json = element.asJsonObject

                        try {
                            // Find bedrock recipe and create it.
                            ConiumBedrockRecipeBuilder.findBedrock(json, this.registries).let { builder ->
                                val recipes = builder.build()
                                val standalone = recipes.size == 1
                                for ((index, recipe) in recipes.withIndex()) {
                                    val id = if (standalone) {
                                        identifier2
                                    } else {
                                        Identifier.of(identifier2.namespace, identifier2.path + "_" + recipe.type.coniumName + "_" + index)
                                    }

                                    check(result.putIfAbsent(id, recipe) == null) {
                                        "Duplicate data file ignored with ID $id"
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            LOGGER.error("Couldn't parse data file '{}' from '{}'", identifier2, key, e)
                        }
                    }
                } catch (var14: Throwable) {
                    try {
                        reader.close()
                    } catch (var13: Throwable) {
                        var14.addSuppressed(var13)
                    }

                    throw var14
                }

                reader.close()
            } catch (var15: Exception) {
                LOGGER.error("Couldn't parse data file '{}' from '{}'", identifier2, key, var15)
            }
        }
    }

    override fun apply(preparedRecipes: PreparedRecipes, resourceManager: ResourceManager, profiler: Profiler) {
        this.preparedConiumRecipes = preparedRecipes
        LOGGER.info("Loaded {} recipes", preparedRecipes.recipes().size)
    }

    override fun initialize(features: FeatureSet) {
        val list: MutableList<CuttingRecipeDisplay.GroupEntry<StonecuttingRecipe>> = ArrayList()
        val list2 = SOLE_INGREDIENT_GETTERS.entries
            .stream()
            .map { entry ->
                PropertySetBuilder(
                    entry.key, entry.value
                )
            }
            .toList()
        this.preparedConiumRecipes
            .recipes()
            .forEach {
                val recipe2 = it.value()
                if (recipe2.isIgnoredInRecipeBook || !recipe2.ingredientPlacement.hasNoPlacement()) {
                    list2.forEach { builder -> builder.accept(recipe2) }
                    if (recipe2 is StonecuttingRecipe && isEnabled(features, recipe2.ingredient()) && recipe2.createResultDisplay().isEnabled(features)) {
                        list.add(
                            CuttingRecipeDisplay.GroupEntry(
                                recipe2.ingredient(),
                                CuttingRecipeDisplay(
                                    recipe2.createResultDisplay(),
                                    Optional.of(it as RecipeEntry<StonecuttingRecipe>)
                                )
                            )
                        )
                    }
                } else {
                    LOGGER.warn("Recipe {} can't be placed due to empty ingredients and will be ignored", it.id().value)
                }
            }
        this.coniumPropertySets = list2.stream().collect(
            Collectors.toUnmodifiableMap(
                PropertySetBuilder::propertySetKey
            ) { it.build(features) }
        ) as Map<RegistryKey<RecipePropertySet>, RecipePropertySet>
        this.coniumStonecutterRecipes = CuttingRecipeDisplay.Grouping(list)
        this.coniumRecipes = ServerRecipeManagerAccessor.collectServerRecipes(preparedConiumRecipes.recipes(), features)
        this.coniumRecipesByKey = coniumRecipes
            .stream()
            .collect(
                Collectors.groupingBy(
                    { it.parent.id() },
                    { IdentityHashMap() },
                    Collectors.toList()
                )
            ) as Map<RegistryKey<Recipe<*>>, List<ServerRecipe>>
    }


    override fun <I : RecipeInput, T : Recipe<I>> getFirstMatch(
        type: RecipeType<T>, input: I, world: World, recipe: RegistryKey<Recipe<*>>?
    ): Optional<RecipeEntry<T>> {
        val recipeEntry = if (recipe != null) this.get(type, recipe) else null
        return this.getFirstMatch(type, input, world, recipeEntry)
    }

    override fun <I : RecipeInput, T : Recipe<I>> getFirstMatch(
        type: RecipeType<T>, input: I, world: World, recipe: RecipeEntry<T>?
    ): Optional<RecipeEntry<T>> {
        return if (recipe != null && recipe.value()!!.matches(input, world)) Optional.of(recipe) else this.getFirstMatch(type, input, world)
    }

    /**
     * {@return a recipe of the given {@code type} that match the given
     * * {@code inventory} and {@code world}}
     *
     *
     * If there are multiple matching recipes, the result is arbitrary,
     * but this method will return the same result unless the recipes in this
     * manager are updated.
     *
     * @param type the desired recipe type
     * @param world the input world
     */
    override fun <I : RecipeInput, T : Recipe<I>> getFirstMatch(type: RecipeType<T>, input: I, world: World): Optional<RecipeEntry<T>> {
        return this.preparedConiumRecipes.find(type, input, world).findFirst()
    }

    /**
     * {@return a recipe with the given {@code id}, or empty if there is no such recipe}
     */
    override fun get(key: RegistryKey<Recipe<*>>): Optional<RecipeEntry<*>> {
        return Optional.ofNullable(
            this.preparedConiumRecipes[key]
        )
    }

    /**
     * {@return a recipe with the given {@code id} and {@code type}, or empty if there is no such recipe}
     *
     * @param type the type of the desired recipe
     */
    private fun <T : Recipe<*>> get(type: RecipeType<T>, key: RegistryKey<Recipe<*>>): RecipeEntry<T>? {
        val recipeEntry = this.preparedConiumRecipes[key]
        return if (recipeEntry != null && recipeEntry.value().type == type) {
            recipeEntry as RecipeEntry<T>
        } else {
            null
        }
    }

    override fun getPropertySets(): Map<RegistryKey<RecipePropertySet>, RecipePropertySet> {
        return this.coniumPropertySets
    }

    override fun getStonecutterRecipeForSync(): CuttingRecipeDisplay.Grouping<StonecuttingRecipe> {
        return this.stonecutterRecipes
    }

    override fun getPropertySet(key: RegistryKey<RecipePropertySet>): RecipePropertySet {
        return propertySets.getOrDefault(key, RecipePropertySet.EMPTY)
    }

    override fun getStonecutterRecipes(): CuttingRecipeDisplay.Grouping<StonecuttingRecipe> {
        return this.coniumStonecutterRecipes
    }

    /**
     * {@return all recipes in this manager}
     *
     *
     * The returned set does not update with the manager. Modifications to the
     * returned set does not affect this manager.
     */
    override fun values(): Collection<RecipeEntry<*>> {
        return this.preparedConiumRecipes.recipes()
    }

    override fun get(id: NetworkRecipeId): ServerRecipe {
        return this.coniumRecipes[id.index]
    }

    override fun forEachRecipeDisplay(key: RegistryKey<Recipe<*>>, action: Consumer<RecipeDisplayEntry>) {
        val list = this.coniumRecipesByKey[key]
        list?.forEach { action.accept(it.display) }
    }
}
