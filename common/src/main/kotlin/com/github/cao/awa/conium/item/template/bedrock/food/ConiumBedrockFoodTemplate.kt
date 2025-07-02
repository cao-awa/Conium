package com.github.cao.awa.conium.item.template.bedrock.food

import com.github.cao.awa.conium.item.template.ConiumItemTemplate
import com.github.cao.awa.conium.item.template.consumable.ConiumConsumableTemplate
import com.github.cao.awa.conium.kotlin.extent.json.createIfJsonObject
import com.github.cao.awa.conium.template.ConiumTemplates.BedrockItem.FOOD
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.FoodComponent
import net.minecraft.component.type.UseRemainderComponent
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

class ConiumBedrockFoodTemplate() : ConiumItemTemplate(name = FOOD) {
    companion object {
        @JvmStatic
        fun create(element: JsonElement): ConiumBedrockFoodTemplate = element.createIfJsonObject(
            {
                // Create food template.
                ConiumBedrockFoodTemplate(element.asJsonObject)
            },
            notSupported()
        )!!

        private fun createFoodComponent(template: ConiumBedrockFoodTemplate, jsonObject: JsonObject): FoodComponent {
            FoodComponent.Builder().let {
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

    private lateinit var foodComponent: FoodComponent
    private var useRemainder: ItemStack = ItemStack.EMPTY

    constructor(element: JsonElement) : this() {
        this.foodComponent = createFoodComponent(this, element.asJsonObject)
    }

    override fun settings(settings: Item.Settings) {
        // Set food component
        settings.food(this.foodComponent)

        // Set using convert to stack.
        this.useRemainder.let {
            settings.component(DataComponentTypes.USE_REMAINDER, UseRemainderComponent(it))
        }
    }
}
