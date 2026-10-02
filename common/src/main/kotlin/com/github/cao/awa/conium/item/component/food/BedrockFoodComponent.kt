package com.github.cao.awa.conium.item.component.food

import com.github.cao.awa.conium.exception.Exceptions.notSupported
import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.item.template.consumable.ConiumConsumableTemplate
import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.item.bedrock.BedrockItemComponents.FOOD
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.core.component.DataComponents
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.component.UseRemainder
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ItemStackTemplate

class BedrockFoodComponent() : ConiumItemTemplate(name = FOOD) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): BedrockFoodComponent = element.createIfJsonObject(
            {
                // Create food template.
                BedrockFoodComponent(element.asJsonObject)
            },
            notSupported()
        )!!

        private fun createFoodComponent(template: BedrockFoodComponent, jsonObject: JsonObject): FoodProperties {
            FoodProperties.Builder().let {
                if (jsonObject.has("nutrition")) {
                    it.nutrition(jsonObject["nutrition"].asInt)
                }

                if (jsonObject.has("saturation_modifier")) {
                    it.saturationModifier(jsonObject["saturation_modifier"].asFloat)
                }

                if (jsonObject.has("can_always_eat") && jsonObject["can_always_eat"].asBoolean) {
                    it.alwaysEdible()
                }

                ConiumConsumableTemplate.createConvert(jsonObject, "using_converts_to") { remainder: ItemStack ->
                    template.useRemainder = remainder
                }

                return it.build()
            }
        }
    }

    private lateinit var foodComponent: FoodProperties
    private var useRemainder: ItemStack = ItemStack.EMPTY

    constructor(element: JsonElement) : this() {
        this.foodComponent = createFoodComponent(this, element.asJsonObject)
    }

    override fun settings(settings: Item.Properties) {
        // Set food component
        settings.food(this.foodComponent)

        // Set using convert to stack.
        if (!this.useRemainder.isEmpty) {
            settings.component(DataComponents.USE_REMAINDER, UseRemainder(ItemStackTemplate.fromNonEmptyStack(this.useRemainder)))
        }
    }
}
