package com.github.cao.awa.conium.item.template.food

import com.github.cao.awa.conium.item.ConiumItem
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.component.type.FoodComponent
import net.minecraft.component.type.FoodComponents.*
import net.minecraft.item.Item

class ConiumFoodTemplate(val foodComponent: FoodComponent) : ConiumItemTemplate("food") {
    override fun attach(item: ConiumItem) {

    }

    override fun settings(settings: Item.Settings) {
        settings.food(this.foodComponent)
    }

    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumFoodTemplate {
            if (element is JsonObject) {
                return ConiumFoodTemplate(createFoodComponent(element.asJsonObject))
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

        fun createFoodComponent(jsonObject: JsonObject): FoodComponent {
            FoodComponent.Builder().let {
                if (jsonObject.has("nutrition")) {
                    it.nutrition(jsonObject.get("nutrition").asInt)
                }

                if (jsonObject.has("saturation")) {
                    it.saturationModifier(jsonObject.get("saturation").asFloat)
                }

                if (jsonObject.has("can_always_eat") && jsonObject.get("can_always_eat").asBoolean) {
                    it.alwaysEdible()
                }

                if (jsonObject.has("snack") && jsonObject.get("snack") is JsonObject) {
                    it.snack()
                }

                return it.build()
            }
        }
    }
}
