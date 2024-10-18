package com.github.cao.awa.conium.item.template.food

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.mixin.item.food.FoodComponentBuilderAccessor
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.mojang.serialization.JsonOps
import net.minecraft.component.type.FoodComponent
import net.minecraft.component.type.FoodComponent.StatusEffectEntry
import net.minecraft.component.type.FoodComponents.*
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryOps
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.util.Identifier
import java.util.*

class ConiumFoodTemplate(val foodComponent: FoodComponent) : ConiumItemTemplate("food") {
    companion object {
        @JvmStatic
        fun create(element: JsonElement, registryLookup: WrapperLookup): ConiumFoodTemplate {
            if (element is JsonObject) {
                return ConiumFoodTemplate(createFoodComponent(element.asJsonObject, registryLookup))
            }

            val presetName = element.asString

            val preset = when (presetName) {
                "apple" -> APPLE
                "baked_potato" -> BAKED_POTATO
                "beef" -> BEEF
                "beetroot" -> BEETROOT
                "beetroot_soup" -> BEETROOT_SOUP
                "bread" -> BREAD
                "carrot" -> CARROT
                "chicken" -> CHICKEN
                "chorus_fruit" -> CHORUS_FRUIT
                "cod" -> COD
                "cooked_beef" -> COOKED_BEEF
                "cooked_chicken" -> COOKED_CHICKEN
                "cooked_cod" -> COOKED_COD
                "cooked_mutton" -> COOKED_MUTTON
                "cooked_porkchop" -> COOKED_PORKCHOP
                "cooked_rabbit" -> COOKED_RABBIT
                "cooked_salmon" -> COOKED_SALMON
                "cookie" -> COOKIE
                "dried_kelp" -> DRIED_KELP
                "enchanted_golden_apple" -> ENCHANTED_GOLDEN_APPLE
                "golden_apple" -> GOLDEN_APPLE
                "golden_carrot" -> GOLDEN_CARROT
                "honey_bottle" -> HONEY_BOTTLE
                "melon_slice" -> MELON_SLICE
                "mushroom_stem" -> MUSHROOM_STEW
                "mutton" -> MUTTON
                "poisonous_potato" -> POISONOUS_POTATO
                "porkchop" -> PORKCHOP
                "potato" -> POTATO
                "pufferfish" -> PUFFERFISH
                "pumpkin_pie" -> PUMPKIN_PIE
                "rabbit" -> RABBIT
                "rabbit_stew" -> RABBIT_STEW
                "rotten_flesh" -> ROTTEN_FLESH
                "salmon" -> SALMON
                "spider_eye" -> SPIDER_EYE
                "suspicious_stew" -> SUSPICIOUS_STEW
                "sweet_berries" -> SWEET_BERRIES
                "flow_berries" -> GLOW_BERRIES
                "tropical_fish" -> TROPICAL_FISH
                "ominous_bottle" -> OMINOUS_BOTTLE
                else -> {
                    throw IllegalArgumentException("No preset food that named by '$presetName'")
                }
            }

            return ConiumFoodTemplate(preset)
        }

        private fun createFoodComponent(jsonObject: JsonObject, registryLookup: WrapperLookup): FoodComponent {
            FoodComponent.Builder().let {
                if (jsonObject.has("nutrition")) {
                    it.nutrition(jsonObject["nutrition"].asInt)
                }

                if (jsonObject.has("saturation")) {
                    it.saturationModifier(jsonObject.get("saturation").asFloat)
                }

                if (jsonObject.has("can_always_eat") && jsonObject["can_always_eat"].asBoolean) {
                    it.alwaysEdible()
                }

                if (jsonObject.has("snack") && jsonObject["snack"] is JsonObject) {
                    it.snack()
                }

                if (jsonObject.has("convert_to")) {
                    jsonObject["convert_to"].let { convert ->
                        var convertTo: ItemStack? = null

                        if (convert.isJsonObject) {
                            val ops: RegistryOps<JsonElement> = registryLookup.getOps(JsonOps.INSTANCE)
                            convertTo = ItemStack.CODEC.parse(ops, convert.asJsonObject).orThrow
                        } else {
                            val item = Registries.ITEM.get(Identifier.of(convert.asString))
                            println("$item :: ${convert.asString}")
                            convertTo = ItemStack(item, 1)
                        }

                        (it as FoodComponentBuilderAccessor).setUsingConvertsTo(Optional.ofNullable(convertTo))
                    }
                }

                if (jsonObject.has("effects")) {
                    jsonObject["effects"].let { convert ->
                        if (convert.isJsonArray) {
                            val ops: RegistryOps<JsonElement> = registryLookup.getOps(JsonOps.INSTANCE)
                            for (statusEffectEntry in StatusEffectEntry.CODEC.listOf()
                                .parse(ops, convert.asJsonArray).orThrow
                            ) {
                                it.statusEffect(statusEffectEntry.effect, statusEffectEntry.probability)
                            }
                        }
                    }
                }

                return it.build()
            }
        }
    }

    override fun attach(item: ConiumItem) {

    }

    override fun complete(item: ConiumItem) {

    }

    override fun settings(settings: Item.Settings) {
        settings.food(this.foodComponent)
    }
}
